package restaurant.Controller.CommandImpl;

import restaurant.Controller.Command;
import restaurant.DAO.UsersDAO;
import restaurant.Models.User;

import javax.servlet.http.HttpServletRequest;


public class RegistrationPOST implements Command {

    private final UsersDAO usersDao = new UsersDAO();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);


        usersDao.insertUser(user);
        return "redirect:/main";
    }
}
