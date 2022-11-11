package control;

import entity.Customer;
import boundary.MenuView;
import boundary.CustomerLoginView;

/**
 * This class controls the sign up, login and main menu for Customers
 */
public class CustomerControl implements MainControl {
	/**
	 * The Customer that is currently logged in
	 */
	private Customer customer;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void begin() {
		while (this.customer == null) {
			int option = MenuView.getMenuOption(
					"Please select an option",
					"Sign up",
					"Login",
					"Exit");

			switch (option) {
				case 1:
					this.customer = CustomerLoginView.signupCustomer();
					break;

				case 2:
					this.customer = CustomerLoginView.loginCustomer();
					break;

				case 3:
					NavigateControl.popOne();
					return;
			}
		}

		displayMenu();
	}

	/**
	 * This method controls the display of the main menu for Customers
	 * Customers can perform various functions at this page
	 */
	private void displayMenu() {
		int option = MenuView.getMenuOption(
				"Welcome " + customer.getName() + "!",
				"View all movies",
				"View movie details",
				"Book a movie ticket",
				"View booking history",
				"View show times",
				"List top 5 movies",
				"Exit");

		switch (option) {
			case 1:
				NavigateControl.load(new DisplayMovieControl(true));
				break;

			case 2:
				NavigateControl.load(new MovieControl(customer));
				break;

			case 3:
				NavigateControl.load(new BookingControl(customer));
				break;

			case 4:
				NavigateControl.load(new BookingHistoryControl(customer));
				break;

			case 5:
				NavigateControl.load(new MovieTimeControl());
				break;

			case 6:
				NavigateControl.load(new TopMoviesControl(true));
				break;

			case 7:
				NavigateControl.popOne();
				break;
		}
	}
}
