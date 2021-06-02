package restaurant.Controller;

import restaurant.Controller.CommandImpl.Exception;
import restaurant.Controller.CommandImpl.MainPageCommand;
import restaurant.Controller.CommandImpl.RegistrationGET;
import restaurant.Controller.CommandImpl.RegistrationPOST;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig){
        commands.put("main", new MainPageCommand());
        commands.put("exception", new Exception());

        //registration, authorization
        commands.put("registration", new RegistrationGET());
        commands.put("registrationPost", new RegistrationPOST());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        path = path.replaceAll("restaurant", "");
        path = path.replaceAll("/", "");

        Command command = commands.getOrDefault(path, new MainPageCommand());
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            page = page.replaceAll("redirect:", "");

            response.sendRedirect("/" + page);
            response.sendRedirect("/restaurant" + page);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}