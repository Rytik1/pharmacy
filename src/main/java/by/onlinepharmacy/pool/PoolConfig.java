package by.onlinepharmacy.pool;

import java.util.ResourceBundle;


public class PoolConfig {

    private static final int DEFAULT_MAX_POOL_SIZE = 15;
    private static final int DEFAULT_MIN_POOL_SIZE = 3;
    private String driverClassName;
    private int maxPoolSize;
    private int minPoolSize;
    private int maxWait;
    private String url;
    private String user;
    private String password;
    private int idleTimeout;
    private int cleanInterval;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        if (maxPoolSize > 0) {
            this.maxPoolSize = maxPoolSize;
        } else {
            this.maxPoolSize = DEFAULT_MAX_POOL_SIZE;
        }
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(int minPoolSize) {
        if (minPoolSize > 0) {
            this.minPoolSize = minPoolSize;
        } else {
            this.minPoolSize = DEFAULT_MIN_POOL_SIZE;
        }
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public int getCleanInterval() {
        return cleanInterval;
    }

    public void setCleanInterval(int cleanInterval) {
        this.cleanInterval = cleanInterval;
    }

    public void initPoolConfig(ResourceBundle poolResource) {
        setDriverClassName(poolResource.getString("db.driverClassName"));
        setUrl(poolResource.getString("db.url"));
        setUser(poolResource.getString("db.user"));
        setPassword(poolResource.getString("db.password"));
        setMaxPoolSize(Integer.parseInt(poolResource.getString("pool.maxPoolSize")));
        setMinPoolSize(Integer.parseInt(poolResource.getString("pool.minPoolSize")));
        setMaxWait(Integer.parseInt(poolResource.getString("pool.maxWait")));
        setIdleTimeout(Integer.parseInt(poolResource.getString("pool.idleTimeout")));
        setCleanInterval(Integer.parseInt(poolResource.getString("pool.cleanInterval")));
    }
}

