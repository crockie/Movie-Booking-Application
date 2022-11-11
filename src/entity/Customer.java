package entity;

/** 
 * This class contains the information of the Customer
 */
public class Customer extends User{
    /**
     * Customer's account username
     */
    private String username;
    /**
     * Customer's name
     */
    private String name;
    /**
     * Customer's mobile number
     */
    private Integer mobileNumber;
    /**
     * Customer's email address
     */
    private String emailAddress;
    /**
     * Customer's account password
     */
    private String password;
    /**
     * This constuctor creates a {@code Customer} object with the given username, name, mobile/handphone number, email address and password
     * @param username the username of the Customer's account
     * @param password the password of the Customer's account
     * @param name the name of the Customer
     * @param mobileNumber the mobile/handphone number of the Customer
     * @param emailAddress the email address of the Customer
     */
    
    public Customer(String username, String password, String name, Integer mobileNumber, String emailAddress) {
        super(username, password);
        this.password = super.getPasswordHashString();
        this.username = username;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }
    /**
     * This method checks if a correct password was entered
     * @param password the password entered which will be checked against the Customer's account hashed password
     * @return true if password matches, false if passwords do not match
     */
    public boolean login(String password) {
        return this.password.equals(toHash(password, this.username));
    }
    /**
     * This method gets the Customer's username
     * @return username of the Customer
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * This method gets the Customer's name
     * @return name of the Customer
     */
    public String getName() {
        return name;
    }
    /**
     * This method sets the Customer's name
     * @param name new name for the Customer
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * This method gets the Customer's mobile number
     * @return mobile/handphone number of the Customer
     */
    public Integer getMobileNumber() {
        return mobileNumber;
    }
    /**
     * This method sets the Customer's mobile number
     * @param mobileNumber new mobile/handphone number of the Customer
     */
    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    /**
     * This method gets the Customer's email address
     * @return email address of the Customer
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    /**
     * This method sets the Customer's email address
     * @param emailAddress new email address of the Customer
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    /**
     * This method sets the Customer's password
     * @param password new password for the Customer's account
     */
    public void setPassword(String password) {
        this.password = toHash(password, this.username);
    }
}
