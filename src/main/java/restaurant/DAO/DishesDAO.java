package restaurant.DAO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Models.Enums.Category;
import restaurant.Models.Dishes;

import java.sql.*;
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

    public List<Dishes> findDishesByOrderId(Integer orderId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<Dishes> dishes = new ArrayList<>();
        try {
            connection = DBConnect.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(
                    "select d.id, d.name, d.price, d.image, d.description, d.category, " +
                    "od.amount, od.total_dish " +
                    "from dishes d join ordered_dishes od on d.id = od.dish_id " +
                    "join orders o on o.id = od.order_id where " +
                    "o.id = ?");
            preparedStatement.setInt(1, orderId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dishes.add(new Dishes(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        resultSet.getString("description"),
                        Category.valueOf(resultSet.getString("category")),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("total_dish")));
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
        return dishes;
    }

    public Dishes findDishByDishId(Integer dishId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Dishes dish = null;
        try {
            connection = DBConnect.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM dishes where id = ?;");
            preparedStatement.setInt(1, dishId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                dish = new Dishes(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        resultSet.getString("description"),
                        Category.valueOf(resultSet.getString("category")));
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
        return dish;
    }

}
