package boundary;

import java.io.Console;
import java.util.Scanner;

import entity.CinemaStaff;
import entity.DatabaseManager;

/**
 * This class displays the login view for a cinema staff
 */
@SuppressWarnings("resource")
public class CinemaStaffLoginView {

	/**
	 * This method displays the login view for a cinema staff
	 * 
	 * @return the cinema staff that logged in
	 */
	public static CinemaStaff loginCinemaStaff() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Cinema Staff Login");
		String username = null;
		
		try{
			System.out.print("Username: ");
			username = sc.nextLine();
		} catch (Exception e) {
			System.out.println("Please enter a valid username");
		}
		
		

		if (!DatabaseManager.getDataBase().checkStaffUsername(username)) {
			System.out.println("Error: Cinema Staff username does not exist");
			return null;
		}

		String password = inputPassword("Password: ");

		CinemaStaff cinemaStaff = DatabaseManager.getDataBase().getCinemaStaff(username, password);

		if (cinemaStaff == null)
			System.out.println("Error: Incorrect password");
		return cinemaStaff;
	}
	/**
	 * This method is used to input the password
	 * 
	 * @param message the message to display to the user
	 * @return the password
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