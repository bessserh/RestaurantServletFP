package restaurant.Controller.CommandImpl;
import restaurant.Controller.Command;
import javax.servlet.http.HttpServletRequest;


public class Exception implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/exception.jsp";
    }
}
