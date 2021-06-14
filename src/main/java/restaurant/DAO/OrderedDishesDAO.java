package restaurant.DAO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Models.OrderedDishes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class OrderedDishesDAO {
    private static final Logger logger = LogManager.getLogger(OrderedDishesDAO.class);

    public OrderedDishes createNewOrderDish(Integer orderId, Integer dishId) throws SQLIntegrityConstraintViolationException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        OrderedDishes orderDish = new OrderedDishes(orderId, dishId, 1);
        int success = 0;
        try {
            connection = DBConnect.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("insert into ordered_dishes (order_id, dish_id, amount) VALUES (?, ?, 1)");
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, dishId);
            success = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (e.getClass().equals(SQLIntegrityConstraintViolationException.class)) {
                throw new SQLIntegrityConstraintViolationException();
            }
            logger.error("SQL exception occurred" + e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("Error closing connection" + e);
            }
        }
        return (success == 1) ? orderDish : null;
    }

    public void increaseOrderDishAmount(Integer orderId, Integer dishId) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBConnect.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("update ordered_dishes set amount = amount+1 where order_id = ? AND dish_id = ?");
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, dishId);
            preparedStatement.executeUpdate();
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
    }

    public void decreaseOrderDishAmount(Integer orderId, Integer dishId) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBConnect.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("update ordered_dishes set amount = amount-1 where order_id = ? AND dish_id = ?");
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, dishId);
            preparedStatement.executeUpdate();
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
    }

    public void removeOrderDish(Integer orderId, Integer dishId) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBConnect.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("delete from ordered_dishes where dish_id = ? and order_id = ?");
            preparedStatement.setInt(1, dishId);
            preparedStatement.setInt(2, orderId);
            preparedStatement.execute();
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
    }

}
