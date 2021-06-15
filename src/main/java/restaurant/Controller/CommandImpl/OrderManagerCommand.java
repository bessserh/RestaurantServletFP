package restaurant.Controller.CommandImpl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.Command;
import restaurant.DAO.OrderedDishesDAO;
import restaurant.DAO.OrdersDAO;

import javax.servlet.http.HttpServletRequest;

public class OrderManagerCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OrderManagerCommand.class);
    private final OrderedDishesDAO orderedDishesDao = new OrderedDishesDAO();
    private final OrdersDAO ordersDao = new OrdersDAO();

    @Override
    public String execute(HttpServletRequest request) {
        String manageOrder = request.getParameter("manageOrder");
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        Integer dishId = Integer.parseInt(request.getParameter("dishId"));
        Double dishPrice = Double.parseDouble(request.getParameter("dishPrice"));

        if (manageOrder.equals("plus")) {
            orderedDishesDao.increaseOrderDishAmount(orderId, dishId);
            ordersDao.changePrice(true, dishPrice, orderId);
            logger.info("increase dish amount, dish_id: " + dishId + " order_id: " + orderId);

        } else if (manageOrder.equals("minus") && Integer.parseInt(request.getParameter("dishAmount")) > 1) {
            orderedDishesDao.decreaseOrderDishAmount(orderId, dishId);
            ordersDao.changePrice(false, dishPrice, orderId);
            logger.info("decrease dish amount, dish_id: " + dishId + " order_id: " + orderId);

        } else if (manageOrder.equals("remove")) {
            orderedDishesDao.removeOrderDish(orderId, dishId);
            ordersDao.changePrice(false, dishPrice, orderId);
            logger.info("remove dish from order, dish_id: " + dishId + " order_id: " + orderId);
        }
        return "redirect:/userCabinet";
    }
}
