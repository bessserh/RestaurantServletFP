package restaurant.Controller.CommandImpl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.Command;
import restaurant.DAO.UsersDAO;
import restaurant.Models.User;

import javax.servlet.http.HttpServletRequest;


public class RegistrationPOST implements Command {

    private static final Logger logger = LogManager.getLogger(RegistrationPOST.class);
    private final UsersDAO usersDao = new UsersDAO();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("checking registration request");

        String name = request.getParameter("login");
        String password = request.getParameter("password");

        if (name == null || name.equals("") || password == null || password.equals("")) {
            return "redirect:/registration?status=noData";
        }

        if (usersDao.verify(name, password) != null) {
            return "redirect:/registration?status=exist";
        } else {
            User user = new User();
            user.setLogin(name);
            user.setPassword(password);
            if (usersDao.insertUser(user) > 0) {
                logger.info("new user registered");
                return "redirect:/login";
            }
            logger.info("user not registered");
            return "redirect:/main";
        }
    }
}
