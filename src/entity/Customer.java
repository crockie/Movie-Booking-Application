package entity;

import java.io.Serializable;

/** 
 * This class contains the information of the Customer
 */
public class Customer implements Serializable{
    
    /**
     * The serialisation version number
     */
    private static final long serialVersionUID = 16456864189484841L;
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
     * Constructor for Customer's account
     * Creates a {@code Customer} object with the given username, name, mobile/handphone number, email address and password
     * @param username the username of the Customer's account
     * @param name the name of the Customer
     * @param mobileNumber the mobile/handphone number of the Customer
     * @param emailAddress the email address of the Customer
     * @param password the password of the Customer's account
     */
    public Customer(String username, String name, Integer mobileNumber, String emailAddress, String password) {
        this.username = username;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.password = password;
    }
    /**
     * Function to check if correct password was entered
     * @param password the password entered which will be checked against the Customer's account password
     * @return true if password matches, false if passwords do not match
     */
    public boolean login(String password) {
        return password.equals(this.password);
    }
    /**
     * Getter for Customer's username
     * @return username of the Customer
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * Getter for Customer's name
     * @return name of the Customer
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for Customer's name
     * @param name new name for the Customer
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter for Customer's mobile number
     * @return mobile/handphone number of the Customer
     */
    public Integer getMobileNumber() {
        return mobileNumber;
    }
    /**
     * Setter for Customer's mobile number
     * @param mobileNumber new mobile/handphone number of the Customer
     */
    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    /**
     * Getter for Customer's email address
     * @return email address of the Customer
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    /**
     * Setter for Customer's email address
     * @param emailAddress new email address of the Customer
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    /**
     * Setter for Customer's password
     * @param new password for the Customer's account
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
