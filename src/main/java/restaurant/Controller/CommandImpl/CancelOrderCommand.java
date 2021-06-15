package restaurant.Controller.CommandImpl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.Command;
import restaurant.DAO.OrdersDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CancelOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CancelOrderCommand.class);
    private final OrdersDAO ordersDao = new OrdersDAO();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());

        if (ordersDao.findOrderByUserId(userId).getStatus().equals("APPROVING")) {
            ordersDao.cancelOrder(orderId);
        }
        logger.info("order canceled, id: " + orderId);
        return "redirect:/userCabinet";
    }

}