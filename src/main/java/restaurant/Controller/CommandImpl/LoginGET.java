package restaurant.Controller.CommandImpl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.Command;

import javax.servlet.http.HttpServletRequest;

public class LoginGET implements Command {

    private static final Logger logger = LogManager.getLogger(LoginGET.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("login page");
        String status = "";
        String statusParam = request.getParameter("status");
        if(statusParam!=null) {
            switch (statusParam) {
                case "logged":
                    status = "User already logged";
                    logger.info("already logged");
                    break;
                case "noData":
                    status = "Enter login and password";
                    logger.info("empty request");
                    break;
                case "blocked":
                    status = "User is blocked";
                    logger.info("blocked");
                    break;
                case "no":
                    status = "Login or password is incorrect";
                    logger.info("incorrect data");
                    break;
            }
        }
        request.setAttribute("loginStatus", status);
        return "/login.jsp";
    }
}
