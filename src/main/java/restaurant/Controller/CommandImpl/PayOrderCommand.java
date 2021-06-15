package restaurant.Controller.CommandImpl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.Command;
import restaurant.DAO.OrdersDAO;
import restaurant.Models.Orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PayOrderCommand implements Command {

    private final OrdersDAO ordersDao = new OrdersDAO();
    private static final Logger logger = LogManager.getLogger(PayOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        Orders order = ordersDao.findOrderByUserId(userId);
        ordersDao.payOrder(order.getId());
        logger.info("payed order " + order.getId());
        return "redirect:/userCabinet";
    }
}