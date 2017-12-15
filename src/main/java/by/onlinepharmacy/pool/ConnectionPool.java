package by.onlinepharmacy.pool;

 import by.onlinepharmacy.exception.ConnectionPoolException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);

    private PoolConfig poolConfig;
    private boolean isDriverRegister;
    private AtomicInteger usedConnectionNumber;
    private AtomicLong startIdleTime;
    private ScheduledExecutorService poolCleanService;
    private AtomicBoolean isClosed;
    private BlockingQueue<ProxyConnection> connectionQueue;
    private static ConnectionPool pool;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Lock poolLock = new ReentrantLock();

    private ConnectionPool() {
        try {
            ResourceBundle poolResource = ResourceBundle.getBundle("configuration.poolconfig");
            poolConfig = new PoolConfig();
            poolConfig.initPoolConfig(poolResource);

            //register driver only once
            if (!isDriverRegister) {
                Class.forName(poolConfig.getDriverClassName());
                isDriverRegister = true;
            }

            usedConnectionNumber = new AtomicInteger(0);
            isClosed = new AtomicBoolean(false);
            startIdleTime = new AtomicLong(System.currentTimeMillis());

            //create the initial connection queue
            connectionQueue = new ArrayBlockingQueue<>(poolConfig.getMaxPoolSize());
            for (int i = 0; i < poolConfig.getMinPoolSize(); ++i) {
                connectionQueue.add(createConnection());
            }

            //initialize and run the cleaner thread
            poolCleanService = Executors.newSingleThreadScheduledExecutor();
            poolCleanService.scheduleWithFixedDelay(new PoolCleaner(), 0, poolConfig.getCleanInterval(), TimeUnit.SECONDS);
            logger.info("ConnectionPool has been successfully created.");

        } catch (ClassNotFoundException | SQLException | NumberFormatException | MissingResourceException e) {
            logger.fatal("Can't create connection pool: ", e);
            throw new RuntimeException("Problem when create connection pool! ", e);
        }

    }

    //Returns the single instance ConnectionPool .
    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            try {
                poolLock.lock();
                if (pool == null) {
                    pool = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                poolLock.unlock();
            }
        }
        return pool;
    }

    //Gets instance of  ProxyConnection for access to database.
    public ProxyConnection getConnection() throws ConnectionPoolException {

        if (isClosed.get()) {
            throw new RuntimeException("Connection pool is in the process of closing, connections are not available");
        }

        startIdleTime.set(System.currentTimeMillis());
        ProxyConnection proxyConnection = null;

        try {
            poolLock.lock();
            //if there aren't available connections in pool and can add connection without pool oversizing
            if (connectionQueue.isEmpty() && usedConnectionNumber.get() < poolConfig.getMaxPoolSize()) {
                try {
                    proxyConnection = createConnection();
                } catch (SQLException e) {
                    logger.error("Can't create connection: ", e);
                }
            } else {
                try {
                    //try to get connection from the pool within a specified time
                    proxyConnection = connectionQueue.poll(poolConfig.getMaxWait(), TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    logger.error("Interrupt when get connection: ", e);
                }
            }
            if (proxyConnection != null) {
                usedConnectionNumber.incrementAndGet();
            } else {
                throw new ConnectionPoolException("Can't get connection to the database");
            }
        } finally {
            poolLock.unlock();
        }
        return proxyConnection;
    }

    private ProxyConnection createConnection() throws SQLException {
        return new ProxyConnection(DriverManager.getConnection(poolConfig.getUrl(), poolConfig.getUser(), poolConfig.getPassword()));
    }

    //Returns instance  ProxyConnection  into the pool.
    void returnConnection(ProxyConnection proxyConnection) {
        boolean isReturn = connectionQueue.offer(proxyConnection);
        //shouldn't happen
        if (isReturn) {
            usedConnectionNumber.decrementAndGet();
        } else {
            try {
                proxyConnection.closeConnection();
            } catch (SQLException e) {
                logger.fatal("Can't close connection: ", e);
                throw new RuntimeException("Problem when close connection: ", e);
            }
        }
    }

    //Closes all connections in the pool.
    public void closePool() {
        isClosed.set(true);
        //shutdown the cleaner thread
        poolCleanService.shutdownNow();
        try {

            for (ProxyConnection connection : connectionQueue) {
                connection.closeConnection();
            }

            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }


        } catch (SQLException e) {
            logger.error("Problem when closing connection or deregister driver: ", e);

        }
    }

    //Class is responsible for cleaning up the pool of idle connections.
    private class PoolCleaner implements Runnable {
        private static final int TO_MILLISECONDS = 1000;

        @Override
        public void run() {
            while (connectionQueue.size() > poolConfig.getMinPoolSize() &&
                    (System.currentTimeMillis() - startIdleTime.get()) >= (poolConfig.getIdleTimeout() * TO_MILLISECONDS)) {
                try {
                    //try to get and close connection
                    ProxyConnection proxyConnection = connectionQueue.poll(poolConfig.getMaxWait(), TimeUnit.SECONDS);
                    if (proxyConnection != null) {
                        proxyConnection.closeConnection();
                    }
                } catch (SQLException e) {
                    logger.error("Problem when closing connection: ", e);
                    throw new RuntimeException("Problem when closing connection: ", e);
                } catch (InterruptedException e) {
                    logger.error("Interrupt when closing connection: ", e);
                }
            }
        }
    }
}

