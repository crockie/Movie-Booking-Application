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
	 * 
	 * @return the logged in cinema staff
	 */
	public static CinemaStaff loginCinemaStaff() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Cinema Staff Login");
		System.out.print("Username: ");
		String username = sc.nextLine();

		if (!DatabaseManager.getDataBase().checkStaffUsername(username)) {
			System.out.println("Error: User with that that username doesn't exist");
			return null;
		}

		String password = inputPassword("Password: ");

		CinemaStaff cinemaStaff = DatabaseManager.getDataBase().getCinemaStaff(username, password);

		if (cinemaStaff == null)
			System.out.println("Error: Incorrect password");
		return cinemaStaff;
	}

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