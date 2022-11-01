package control;

import entity.Customer;
import view.MenuView;
import view.CustomerLoginView;

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
				"Exit"
			);
			
			switch (option) {
				case 1:
					this.customer = CustomerLoginView.signupCustomer();
					break;
					
				case 2:
					this.customer = CustomerLoginView.loginCustomer();
					break;
					
				case 3:
					NavigationController.goBack();
					return;
			}
		}
		
		displayMenu();
	}
	
	/**
	 * This function controls the display of the main menu for Customers
	 * Customers can perform various functions at this page
	 */
	private void displayMenu() {
		int option = MenuView.getMenuOption(
			"Welcome " + customer.getName() + "!",
			"View movie times",
			"Book a movie ticket",
			"View movie details",
			"List top 5 movies",
			"View booking history",
			"Exit"
		);
		
		switch (option) {
			case 1:
				NavigationController.load(new MovieTimeControl());
				break;
				
			case 2:
				NavigationController.load(new BookingController(customer));
				break;
				
			case 3:
				NavigationController.load(new MovieControl(customer));
				break;
				
				
			case 4:
				NavigationController.load(new TopMoviesControl());
				break;
				
			case 5:
				NavigationController.load(new BookingHistoryControl(customer));
				break;
				
			case 6:
				NavigationController.goBack();
				break;
		}
	}
}
