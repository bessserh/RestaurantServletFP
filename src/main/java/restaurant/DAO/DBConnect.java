package restaurant.DAO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnect {

    private static DBConnect instance;
    private boolean isTest;
    private static final Logger log = LogManager.getLogger(DBConnect.class);

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
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }

        try {
            context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/restaurant");
            connection = ds.getConnection();
            log.info("Get connection by pool");
        } catch (NamingException | SQLException e) {
            log.error("Error while connection pooling ", e);
        }
        return connection;
    }


    private Connection getConnectionForTests() {
        return null;
    }


}
