import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restaurant.DAO.DBConnect;
import restaurant.DAO.DishesDAO;
import restaurant.Models.Dishes;

import java.util.List;

public class DishesDaoTest {
    DBConnect dbManager;
    private DishesDAO dishesDao = new DishesDAO();

    @Before
    public void setTestTrue() {
        dbManager= DBConnect.getInstance(true);
    }

    @Test
    public void findAllDishes(){
        List<Dishes> dishesList = dishesDao.getAllDishes();
        Assert.assertNotNull(dishesList);
    }

    @Test
    public void findDishById(){
        Dishes dish = dishesDao.findDishByDishId(3);
        Integer expected = 3;
        Assert.assertEquals(expected, dish.getId());
    }
}