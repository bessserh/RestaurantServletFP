import at.favre.lib.crypto.bcrypt.BCrypt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import restaurant.DAO.DBConnect;
import restaurant.DAO.UsersDAO;
import restaurant.Models.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {
    DBConnect dbManager;
    private final UsersDAO usersDao = new UsersDAO();

    @Before
    public void setTestTrue() {
        dbManager= DBConnect.getInstance(true);
    }

    @Test
    public void testRegister() {
        User user = new User();
        user.setLogin("login");
        user.setPassword(BCrypt.withDefaults().hashToString(15, "password".toCharArray()));
        Integer rowsAffected = usersDao.insertUser(user);
        Integer expected = 1;
        Assert.assertEquals(expected, rowsAffected);
    }

    @Test
    public void testSValidation() {
        User user = usersDao.verify("login", "password");
        Assert.assertNotNull(user);
    }

    @Test
    public void testUserDelete() {
        User user = new User();
        user.setLogin("login");
        Integer rowsAffected = usersDao.deleteUser(user);
        Integer expected = 1;
        Assert.assertEquals(expected, rowsAffected);
    }

}
