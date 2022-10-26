package Entity;

import java.io.Serializable;

public class Customer implements Serializable{
    
	/**
     * The serialisation version number
     */
    private static final long serialVersionUID = 7689870075709929042L;
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
     */
    public boolean login(String password) {
        return password.equals(this.password);
    }
    /**
     * Getter for Customer's username
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * Getter for Customer's name
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for Customer's name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter for Customer's mobile number
     */
    public Integer getMobileNumber() {
        return mobileNumber;
    }
    /**
     * Setter for Customer's mobile number
     */
    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    /**
     * Getter for Customer's email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    /**
     * Setter for Customer's email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    /**
     * Setter for Customer's password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
