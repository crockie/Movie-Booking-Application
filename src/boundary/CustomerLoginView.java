package boundary;

import entity.*;

/**
 * This class displays the login and signup form for the customer
 */
public class CustomerLoginView {
	/**
	 * This method reads in an entered password
	 * @return the String password
	 */
	public static String inputPassword(String message) {
		Console console = System.console();
		if (console != null) {
			char[] passwordArray = console.readPassword(message);
		    return new String(passwordArray);

		} 
		else {
			// Fallback
			System.out.print(message);
			Scanner sc = new Scanner(System.in);
			return sc.nextLine();
		}
	}
	
	/**
	 * This method displays the signup form for the customer
	 * @return the newly signed up customer
	 */
	public static Customer signupCustomer() {
		
		System.out.print("Username: ");
		Scanner sc = new Scanner(System.in);
		String username = sc.nextLine();
		
		if (DatabaseManager.getDataBase().checkCustomerUsername(username)) {
			System.out.println("Error: User with that username already exists");
			return null;
		}
		
		System.out.print("Name: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		int mobileNumber=0;
		while (true) {
			try {
				System.out.print("Mobile Number: ");
				Scanner sc = new Scanner(System.in);
				mobileNumber = sc.nextInt();
				break;

			} catch (InputMismatchException e) {
				System.out.println("Please enter an integer");
			}
		}
		
		System.out.print("Email Address: ");
		Scanner sc = new Scanner(System.in);	
		String emailAddress = sc.nextLine();
		
		String password1 = "", password2;
		
		while (true){
			password1 = inputPassword("Password: ");
			password2 = inputPassword("Confirm Password: ");
			
			if (password1.equals(password2))
				break;
			else
				System.out.println("Error: Password mismatch");
		}
		
		
		Customer customer = new Customer(username, name, mobileNumber, emailAddress, password1);
		
		if (!DatabaseManager.getDataBase().addCustomer(customer))
			System.out.println("Error: Unable to add customer");
		
		return customer;
	}
	
	/**
	 * This method displays the login form for the customer
	 * @return the logged in customer
	 */
	public static Customer loginCustomer() {
		System.out.print("Username: ");
		Scanner sc = new Scanner(System.in);
		String username = sc.nextLine();
		
		if (!DatabaseManager.getDataBase().checkCustomerUsername(username)) {
			System.out.println("Error: User with that that username doesn't exist");
			return null;
		}
		
		String password = inputPassword("Password: ");
		
		Customer customer = DatabaseManager.getDataBase().getCustomer(username, password);
		
		if (customer == null)
			System.out.println("Error: Incorrect password");
		
		return customer;
	}
}
