package restaurant.Controller.CommandImpl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.Command;
import restaurant.Controller.CommandUtility;
import restaurant.DAO.UsersDAO;
import restaurant.Models.Role;
import restaurant.Models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginPOST implements Command {
    private static final Logger logger = LogManager.getLogger(LoginPOST.class);
    private final UsersDAO usersDao = new UsersDAO();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("checking data from login");

        String name = request.getParameter("login");
        String password = request.getParameter("password");

        if (name == null || name.equals("") || password == null || password.equals("")) {
            request.setAttribute("loginStatus", "Enter login and password");
            return "redirect:/login?status=noData";
        }

        if (CommandUtility.checkUserIsLogged(request, name)) {
            return "redirect:/login?status=logged";
        }

        User user = usersDao.verify(name, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(5 * 60);
            session.setAttribute("userName", user.getLogin());
            session.setAttribute("userId", user.getId());
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("userActive", user.isActive());
            if (user.getRole().equals("ADMIN") && user.isActive()) {
                logger.info("login as admin");
                CommandUtility.setUserRole(request, Role.ADMIN, name);
            } else if (user.getRole().equals("USER") && user.isActive()) {
                logger.info("login as user");
                CommandUtility.setUserRole(request, Role.USER, name);
            } else {
                logger.info("user blocked");
                return "redirect:/login?status=blocked";
            }
            return "redirect:/main";
        } else {
            return "redirect:/login?status=no";
        }
    }
}
