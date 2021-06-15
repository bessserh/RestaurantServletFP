package restaurant.Controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.Controller.CommandImpl.*;
import restaurant.Controller.CommandImpl.Exception;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(Servlet.class);

    public void init(ServletConfig servletConfig){
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        //general COMMANDS
        commands.put("main", new MainPageCommand());
        commands.put("logout", new LogOutCommand());
        //commands.put("exception", new Exception());
        //registration, authorization
        commands.put("registration", new RegistrationGET());
        commands.put("registrationPost", new RegistrationPOST());
        commands.put("login", new LoginGET());
        commands.put("loginPost", new LoginPOST());
        //user cabinet -> all orders commands
        commands.put("userCabinet", new UserCabinetGET());
        commands.put("userCabinetPost", new UserCabinetPOST());
        commands.put("userCabinetPay", new PayOrderCommand());
        commands.put("userCancelOrder", new CancelOrderCommand());
        commands.put("manageOrder", new OrderManagerCommand());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Servlet start");

        String path = request.getRequestURI();
        logger.info("request URI " + path);

        path = path.replaceAll("restaurant", "");
        path = path.replaceAll("/", "");

        Command command = commands.getOrDefault(path,
                new MainPageCommand());
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            page = page.replaceAll("redirect:", "");
            logger.info("redirect to page " + page);
            response.sendRedirect("/restaurant" + page);
        } else {
            logger.info("forward to page " + page);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}