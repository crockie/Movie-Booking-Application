package Entity;

public class CinemaStaff {
    private String username;
    private String password;

    public CinemaStaff(String us, String pwd) {
        username = us;
        password = pwd;
    }

    public void setPassword(String pwd) {
        password = pwd;
    }

    public boolean login(String pwd) {
        if (password.equals(pwd))
            return true;
        else
            return false;
    }

    public String getUsername() {
        return username;
    }

}