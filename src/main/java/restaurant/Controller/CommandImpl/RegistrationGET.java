package restaurant.Controller.CommandImpl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.Command;

import javax.servlet.http.HttpServletRequest;

public class RegistrationGET implements Command {

    private static final Logger logger = LogManager.getLogger(RegistrationGET.class);

    @Override
    public String execute(HttpServletRequest request) {
        String status = "";
        String statusParam = request.getParameter("status");
        logger.info("registration");

        if(statusParam!=null) {
            switch (statusParam) {
                case "exist":
                    status = "User already exists";
                    logger.info("user exist");
                    break;
                case "noData":
                    status = "Enter login and password";
                    logger.info("empty request");
                    break;
            }
        }
        request.setAttribute("registrationStatus", status);
        return "/registration.jsp";
    }
}
