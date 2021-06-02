package restaurant.DAO;

import sun.security.jca.GetInstance;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static DBConnect instance;
    private Context context;

    public static DBConnect getInstance() {
        if(instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }

    public Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/restaurant");
            connection = ds.getConnection();
            System.out.println("connected");
        } catch (SQLException | NamingException throwables) {
            System.out.println("not connected");
            System.out.println(throwables.toString());
            //logger;
        }
        return connection;
    }
}
