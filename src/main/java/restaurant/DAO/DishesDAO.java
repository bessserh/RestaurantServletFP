package restaurant.DAO;

import restaurant.Models.Dishes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DishesDAO {


    public List<Dishes> getAllDishes() {
        List<Dishes> menu = new ArrayList<>();

        try {
            Connection connection = DBConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dishes;");
            while (resultSet.next()) {
                menu.add(new Dishes()); //add all
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return menu;
    }

}
