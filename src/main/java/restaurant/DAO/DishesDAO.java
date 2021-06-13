package restaurant.DAO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Models.Category;
import restaurant.Models.Dishes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DishesDAO {

    private static final Logger logger = LogManager.getLogger(DishesDAO.class);

    public List<Dishes> getAllDishes() {
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Dishes> menu = new ArrayList<>(); //all

        try {
            connection = DBConnect.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dishes;");
            while (resultSet.next()) {
                menu.add(new Dishes(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        resultSet.getString("description"),
                        Category.valueOf(resultSet.getString("category"))));
            }
        } catch (SQLException e) {
            logger.error("SQL exception occurred" + e);
        } finally {
            try {
                statement.close();
                connection.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error closing connection" + e);
            }
        }
        return menu;
    }

}
