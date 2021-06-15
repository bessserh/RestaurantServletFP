package restaurant.Controller.CommandImpl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.Command;
import restaurant.DAO.OrdersDAO;

import javax.servlet.http.HttpServletRequest;

public class AdminPOST implements Command {
    private final OrdersDAO ordersDao = new OrdersDAO();
    private static final Logger logger = LogManager.getLogger(AdminPOST.class);

    @Override
    public String execute(HttpServletRequest request) {
        Integer userID = Integer.parseInt(request.getParameter("userId"));
        ordersDao.nextStatus(userID);
        logger.info("admin change order status");
        return "redirect:/admin";
    }
}
