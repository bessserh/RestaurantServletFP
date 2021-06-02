package restaurant.Controller.CommandImpl;

import restaurant.Controller.Command;

import javax.servlet.http.HttpServletRequest;

public class RegistrationGET implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/registration.jsp";
    }
}
