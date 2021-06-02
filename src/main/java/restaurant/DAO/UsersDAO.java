package restaurant.DAO;

import restaurant.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersDAO {

    public Integer insertUser(User user) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        try {
            connection = DBConnect.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("insert into users (login, password) values " +
                    "(?, ?);");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // logger
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
               //logger
            }
        }
        return result;
    }
}
