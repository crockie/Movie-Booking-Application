package control;

import java.util.ArrayList;
import java.util.List;

import entity.Booking;
import entity.Cinema;
import entity.Cineplex;
import entity.Customer;
import entity.DatabaseManager;
import entity.MovieTime;
import view.ListView;

/**
 * This class manages the display of the booking history for the Customer
 */
public class BookingHistoryControl implements MainControl {
	/**
	 * The Customer that the booking history is being displayed for
	 */
	private Customer customer;

	/**
	 * Creates a new {@code BookingHistoryControl} object for the current Customer
	 * 
	 * @param customer the Customer that the booking history is being displayed for
	 */
	public BookingHistoryControl(Customer customer) {
		this.customer = customer;
	}

	/**
	 * {@inheritDoc}
	 * Displaying of Booking History for Customer
	 */
	@Override
	public void begin() {
		ListView.displayList("Booking History", getBookingHistory(), "No bookings made by this customer");
		NavigationController.goBack();
	}

	/**
	 * This function goes through all the cineplexes, cinemas, movie times and
	 * bookings to find the Customer's bookings
	 * The information of the bookings is returned as a string
	 * 
	 * @return a {@code List} of {@code String} objects containing the information
	 *         about each of the Customer's bookings
	 */
	public List<String> getBookingHistory() {
		List<String> customerBookingHistory = new ArrayList<String>();

		for (Cineplex cineplex : DatabaseManager.getDataBase().getCineplexList()) {
			for (Cinema cinema : cineplex.getCinemas()) {
				for (MovieTime movieTime : cinema.getMovieTimes()) {
					for (Booking booking : movieTime.getBookings()) {
						if (booking.getCustomer() == customer) {
							customerBookingHistory.add(
									"Transaction ID: " + booking.getTransactionId() + "\n" +
											"Price: $" + String.format("%.2f", booking.getPrice()) + "\n" +
											"Movie: " + movieTime.getMovie().getTitle() + "\n" +
											"Movie time: " + movieTime.nameToString() + "\n" +
											"Cineplex: " + cineplex.getName() + "\n");
						}
					}
				}
			}
		}

		return customerBookingHistory;
	}
}
