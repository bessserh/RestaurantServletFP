package restaurant.Controller.CommandImpl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.Command;
import restaurant.DAO.DishesDAO;
import restaurant.DAO.OrderedDishesDAO;
import restaurant.DAO.OrdersDAO;
import restaurant.Models.Dishes;
import restaurant.Models.Orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserCabinetPOST implements Command {
    private static final Logger logger = LogManager.getLogger(UserCabinetPOST.class);
    private final OrdersDAO ordersDao = new OrdersDAO();
    private final DishesDAO dishesDao = new DishesDAO();
    private final OrderedDishesDAO ordersDishesDao = new OrderedDishesDAO();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Trying to add dish to order");
        HttpSession session = request.getSession();
        if(session.getAttribute("userId")==null || !session.getAttribute("userRole").equals("USER")){
            return "/login.jsp";
        }
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        Orders order = ordersDao.findOrderByUserId(userId);

        if (order == null) {
            order = ordersDao.createNewOrder(userId);
        }

        if(order.getStatus().equals("MAKING")) {
            Object dId = request.getParameter("DishId");
            Integer dishId = Integer.parseInt(dId.toString());

            Dishes dish = dishesDao.findDishByDishId(dishId);

            try {
                ordersDishesDao.createNewOrderDish(order.getId(), dishId);
                logger.info("Dish added");
            } catch (SQLIntegrityConstraintViolationException e) {
                ordersDishesDao.increaseOrderDishAmount(order.getId(), dishId);
                logger.info("Amount increased");
            }
            ordersDao.changePrice(true, dish.getPrice(), order.getId());
        }

        return "redirect:/userCabinet";
    }
}
