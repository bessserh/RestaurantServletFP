package restaurant.Controller.CommandImpl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.Command;
import restaurant.DAO.DishesDAO;
import restaurant.DAO.OrdersDAO;
import restaurant.Models.Dishes;
import restaurant.Models.Orders;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserCabinetGET implements Command {

    private static final Logger logger = LogManager.getLogger(UserCabinetGET.class);
    private final DishesDAO dishesDao = new DishesDAO();
    private final OrdersDAO ordersDao = new OrdersDAO();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Opening user page");

        HttpSession session = request.getSession();
        Object user = session.getAttribute("userId");

        ServletContext context = request.getServletContext();
        request.setAttribute("name", context.getAttribute("userName"));

        if (user != null) {
            Integer userId = Integer.parseInt(user.toString());
            Orders order = ordersDao.findOrderByUserId(userId);

            if (order != null) {
                request.setAttribute("orderStatus", order.getStatus());
                request.setAttribute("orderId", order.getId());
                request.setAttribute("totalPrice", order.getTotal());
                List<Dishes> dishes = dishesDao.findDishesByOrderId(order.getId());
                request.setAttribute("dishes", dishes);
            }
            return "/userCabinet.jsp";
        }
        return "/login.jsp";
    }
}

