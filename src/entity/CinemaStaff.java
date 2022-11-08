package entity;

/**
 * Serves as the login system of cinema staff
 */
public class CinemaStaff extends User {
    /**
     * The username of the cinema staff
     */
    private String username;

    /**
     * The password that the cinema staff uses to login the system
     */
    private String password;

    /**
     * Create a CinemaStaff object with username and password provided
     * 
     * @param us  Username of the cinema staff
     * @param pwd Password of the cinema staff
     */
    public CinemaStaff(String us, String pwd) {
        super(us, pwd);
        this.username = us;
        this.password = super.getPasswordHashString();
    }

    /**
     * For cinema staff to change password
     * 
     * @param pwd New password of the cinema staff
     */
    public void setPassword(String pwd) {
        password = super.toHash(pwd, username);
    }

    /**
     * For cinema staff to login into the system
     * 
     * @param pwd Password inputted by the cinema staff
     * @return If password is matched, return true to allow access
     *         Else, return false to deny access
     */
    public boolean login(String pwd) {
        if (password.equals(super.toHash(pwd, this.username)))
            return true;
        else
            return false;
    }

    /**
     * Get the username of the cinema staff
     * 
     * @return the username of the cinema staff
     */
    public String getUsername() {
        return username;
    }

}