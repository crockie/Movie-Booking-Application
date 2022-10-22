
public class User {
    /**
     * The serialisation version number
     */
    //private static final long serialVersionUID = 7689870075709929042L;
    private String username;
    private String name;
    private Integer mobileNumber;
    private String emailAddress;
    private String password;
    
    
    //Creates a User object with the given username, name, mobile number, email address and password
    public User(String username, String name, Integer mobileNumber, String emailAddress, String password) {
        this.username = username;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.password = password;
    }
    
    /*
     * This method returns true if the login using the password is successful
     * returns true if the password was correct, false if not
     */
    public boolean login(String password) {
        return password.equals(this.password);
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getMobileNumber() {
        return mobileNumber;
    }
    
    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
