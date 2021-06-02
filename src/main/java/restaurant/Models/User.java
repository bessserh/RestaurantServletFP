package restaurant.Models;

public class User {
    private int id;
    private String login;
    private String password;
    private boolean active = true;
    private String role = Role.USER.toString();

    public User(){    }

    public User(String email, String password){
        this.login = email;
        this.password = password;
    }

    public User(int id, String email, String password, boolean active, String role) {
        this.id = id;
        this.login = email;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
