package boundary;

import entity.*;

/**
 * This class displays the login and signup form for the customer
 */
public class CustomerLoginView {
	/**
	 * This method displays the signup form for the customer
	 * @return the newly signed up customer
	 */
	public static Customer signupCustomer() {
		String username = IOController.readLine("Username: ");
		
		if (DatabaseManager.getDataBase().checkCustomerUsername(username)) {
			IOController.displayMessage("Error: User with that username already exists");
			return null;
		}
		
		String name = IOController.readLine("Name: ");
		
		int mobileNumber = IOController.readInt("Mobile Number: ");
		String emailAddress = IOController.readLine("Email Address: ");
		
		String password1 = "", password2;
		
		while (true){
			password1 = IOController.readPassword("Password: ");
			password2 = IOController.readPassword("Confirm Password: ");
			
			if (password1.equals(password2))
				break;
			else
				IOController.displayMessage("Error: Password mismatch");
		}
		
		Customer customer = new Customer(username, name, mobileNumber, emailAddress, password1);
		
		if (!DatabaseManager.getDataBase().addCustomer(customer))
			IOController.displayMessage("Error: Unable to add customer");
		
		return customer;
	}
	
	/**
	 * This method displays the login form for the customer
	 * @return the logged in customer
	 */
	public static Customer loginCustomer() {
		String username = IOController.readLine("Username: ");
		
		if (!DatabaseManager.getDataBase().checkCustomerUsername(username)) {
			IOController.displayMessage("Error: User with that that username doesn't exist");
			return null;
		}
		
		String password = IOController.readPassword("Password: ");
		
		Customer customer = DatabaseManager.getDataBase().getCustomer(username, password);
		
		if (customer == null)
			IOController.displayMessage("Error: Incorrect password");
		
		return customer;
	}
}