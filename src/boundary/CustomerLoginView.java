package boundary;

import java.io.Console;
import java.util.Scanner;

import entity.*;

/**
 * This class displays the login and signup view for a customer
 */
@SuppressWarnings("resource")
public class CustomerLoginView {
	/**
	 * This method displays the signup view for the customer
	 * 
	 * @return the newly signed up customer
	 */
	public static Customer signupCustomer() {

		System.out.print("Username: ");
		Scanner sc = new Scanner(System.in);
		String username = sc.nextLine();

		if (DatabaseManager.getDataBase().checkCustomerUsername(username)) {
			System.out.println("Error: Username already exists");
			return null;
		}

		System.out.print("Name: ");
		String name = sc.nextLine();

		int mobileNumber = 0;
		while (true) {
			try {
				System.out.print("Mobile Number: ");
				mobileNumber = sc.nextInt();
				sc.nextLine();

				if (mobileNumber < 10000000 || mobileNumber > 99999999) {
					System.out.println("Error: Mobile number must be 8 digits");
					continue;
				}
				else if (mobileNumber < 80000000) {
					System.out.println("Error: Mobile number must start with 8 or 9");
					continue;
				}
				break;

			} catch (Exception e) {
				System.out.println("Please enter a valid mobile number");
				sc.nextLine();
			}
		}

		System.out.print("Email Address: ");
		String emailAddress = sc.nextLine();
		while(true) {
			if (emailAddress.contains("@") && emailAddress.contains(".com")) {
				break;
			}
			System.out.println("Please enter a valid email address");
			System.out.print("Email Address: ");
			emailAddress = sc.nextLine();
		}

		String password1 = "", password2;

		while (true) {
			password1 = inputPassword("Password: ");
			password2 = inputPassword("Confirm Password: ");

			if (password1.equals(password2))
				break;
			else
				System.out.println("Error: Password mismatch");
		}

		Customer customer = new Customer(username, password1, name, mobileNumber, emailAddress);

		if (!DatabaseManager.getDataBase().addCustomer(customer))
			System.out.println("Error: Unable to add customer");

		return customer;
	}

	/**
	 * This method displays the login form for the customer
	 * 
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
	/**
	 * This method reads in an entered password
	 * 
	 * @return the String password
	 */
	public static String inputPassword(String message) {
		Console console = System.console();
		if (console != null) {
			char[] passwordArray = console.readPassword(message);
			return new String(passwordArray);

		} else {
			// Fallback
			System.out.print(message);
			Scanner sc = new Scanner(System.in);
			return sc.nextLine();
		}
	}
}
