package restaurant.DAO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static DBConnect instance;
    private boolean isTest;
    private static final Logger logger = LogManager.getLogger(DBConnect.class);

    private DBConnect() {
    }

    private DBConnect(boolean isTest) {
        this.isTest = isTest;
    }

    public static DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }


    public static DBConnect getInstance(boolean isTest) {
        instance = new DBConnect(isTest);
        return instance;
    }


    public Connection getConnection() {
        Context context;
        Connection connection = null;
        if (isTest) {
            return getConnectionForTests();
        }

        try {
            context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/restaurant");
            connection = ds.getConnection();
            logger.info("Get connection by pool");
        } catch (NamingException | SQLException e) {
            logger.error("Error while connection pooling ", e);
        }
        return connection;
    }

    private Connection getConnectionForTests() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException
                | IllegalAccessException | ClassNotFoundException e) {
            logger.error("Error while loading a driver for test. ", e);
        }

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/restaurant_Test?serverTimezone=UTC",
                    "postgres", "sps-606");
        } catch (SQLException throwables) {
            logger.error("Error while getting connection for test. ", throwables);
        }

        return connection;

    }
}

