package restaurant.Controller.CommandImpl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.Command;
import restaurant.DAO.OrdersDAO;
import restaurant.Models.Orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminGET implements Command {
    private final OrdersDAO ordersDao = new OrdersDAO();
    private static final Logger logger = LogManager.getLogger(AdminGET.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Opening admin page");

        HttpSession session = request.getSession();
        if(session.getAttribute("role")!=null) {

            List<Orders> orders = ordersDao.findAllOrders();
            request.setAttribute("orders", orders);

            return "/adminPage.jsp";
        }
        logger.info("End admin command");

        return "/login.jsp";
    }
}
