package restaurant.Controller.CommandImpl;

import restaurant.Controller.Command;
import restaurant.DAO.DishesDAO;
import restaurant.Models.Dishes;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainPageCommand implements Command {
    //static final Logger logger = LogManager.getLogger(MainPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        DishesDAO dishesDao = new DishesDAO();
        List<Dishes> dishesAll = dishesDao.getAllDishes();
        for (Dishes d: dishesAll) {
            System.out.println(d.getName());
        }

        request.setAttribute("dishesList", dishesAll);


        return "/index.jsp";
    }
}
