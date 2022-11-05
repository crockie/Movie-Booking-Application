package boundary;

import java.io.Console;
import java.util.Scanner;

import entity.CinemaStaff;
import entity.DatabaseManager;

/**
 * This class displays the login form for the cinema staff
 */
public class CinemaStaffLoginView {
	
	/**
	 * This method displays the login form for the cinema staff
	 * @return the logged in cinema staff
	 */
	public static CinemaStaff loginCinemaStaff() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Cinema Staff Login");
		System.out.println("Please enter your username: ");
		String username = sc.nextLine();
		
		if (!DatabaseManager.getDataBase().checkStaffUsername(username)) {
			System.out.println("Error: User with that that username doesn't exist");
			return null;
		}
		
		System.out.println("Please enter your password: ");
		Console console = System.console();

		String password;
		if (console != null) {
			password = new String(console.readPassword());
		} else {
			password = sc.nextLine();
		}
		
		System.out.println("Logging in...");
		
		CinemaStaff cinemaStaff = DatabaseManager.getDataBase().getCinemaStaff(username, password);
		
		if (cinemaStaff == null)
			System.out.println("Error: Incorrect password");
		return cinemaStaff;
	}
}