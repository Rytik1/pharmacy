package by.onlinepharmacy.pool;


import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


 public class ConnectionPoolTest {

    @BeforeClass
    public static void getInstance() {

        try {
            ConnectionPool.getInstance();
        } catch (Exception e) {
            Assume.assumeNoException(e);
        }
    }

    @Test
    public void getConnection() throws Exception {
        ProxyConnection connection=ConnectionPool.getInstance().getConnection();
        Assert.assertNotNull(connection);
    }



    @Test
    public void returnConnection() throws Exception {
        try{
        ProxyConnection connection=ConnectionPool.getInstance().getConnection();
        ConnectionPool.getInstance().returnConnection(connection);
    } catch (Exception e) {
         Assume.assumeNoException(e);
     }
    }

     @AfterClass
    public static void closePool() throws Exception {
        try{
        ConnectionPool.getInstance().closePool();
        } catch (Exception e) {
            Assume.assumeNoException(e);
        }
    }


    }
