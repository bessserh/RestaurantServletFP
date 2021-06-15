import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import restaurant.DAO.DBConnect;
import restaurant.DAO.OrdersDAO;
import restaurant.Models.Orders;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdersDaoTest {
    DBConnect dbManager;
    private final Integer USER_ID = 3;
    private final OrdersDAO ordersDao = new OrdersDAO();
    private static Orders order;

    @Before
    public void setTestTrue() {
        dbManager = DBConnect.getInstance(true);
    }

    @Test
    public void createOrder() {
        Orders order = ordersDao.createNewOrder(USER_ID);
        Assert.assertNotNull(order);
    }

    @Test
    public void findOrderByUserId() {
        order = ordersDao.findOrderByUserId(USER_ID);
        Assert.assertEquals(USER_ID, order.getUserId());

        ordersDao.payOrder(order.getId());
        order = ordersDao.findOrderByUserId(USER_ID);
        Assert.assertEquals("APPROVING", order.getStatus());

        ordersDao.nextStatus(USER_ID);
        order = ordersDao.findOrderByUserId(USER_ID);
        Assert.assertEquals("COOKING", order.getStatus());
    }

    @Test
    public void changePrice() {
        ordersDao.changePrice(true, 10.0, order.getId());
        order = ordersDao.findOrderByUserId(USER_ID);
        Double price = 10.0;
        Assert.assertEquals(price, order.getTotal());

        ordersDao.changePrice(false, 10.0, order.getId());
        order = ordersDao.findOrderByUserId(USER_ID);
        price = 0d;
        Assert.assertEquals(price, order.getTotal());
    }

    @Test
    public void cancelOrder() {
        ordersDao.cancelOrder(order.getId());
        order = ordersDao.findOrderByUserId(USER_ID);
        Assert.assertNull(order);
    }

    @Test
    public void deleteOrder() {
        Integer rowsAffected = ordersDao.deleteOrder(USER_ID);
        Integer notExpected = 0;
        Assert.assertNotEquals(notExpected, rowsAffected);
    }

    @Test
    public void findAllOrders() {
        List<Orders> ordersList = ordersDao.findAllOrders();
        Assert.assertNotNull(ordersList);
    }
}
