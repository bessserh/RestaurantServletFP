package restaurant.DAO;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Models.Role;
import restaurant.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {

    private static final Logger logger = LogManager.getLogger(UsersDAO.class);

    public Integer insertUser(User user) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;
        try {
            connection = DBConnect.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("insert into " +
                    "users (email, password, active, role) values " +
                    "(?, ?, ?, ?);");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, true);
            preparedStatement.setString(4, Role.USER.toString());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL exception occurred" + e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("Error closing connection" + e);
            }
        }
        return result;
    }


    public User verify(String email, String password) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        User user = null;

        try {
            connection = DBConnect.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("select * from users where email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("active"),
                        resultSet.getString("role"));
            }
        } catch (SQLException e) {
            logger.error("SQL exception occurred" + e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error closing connection" + e);
            }
        }
        if(user!= null && password.equals(user.getPassword())){
            return user;
        }
        //TODO return null causes a problem
        return null;
    }
}
