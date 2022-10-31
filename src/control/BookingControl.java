//HAVENT CHANGED COMMENTS AND VARIABLES YET
package control;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

import boundary.BookingView;
import boundary.IOController;
import boundary.MenuView;
import entity.AgeGroup;
import entity.Booking;
import entity.Cineplex;
import entity.Customer;
import entity.DatabaseManager;
import entity.Movie;
import entity.MovieTime;
import entity.ShowStatus;
import entity.TicketPrice;

/**
 * This class handles the control flow of movie booking for a customer
 */
public class BookingController implements MainControl {
	/**
	 * The customer who is doing the booking
	 */
	private Customer customer;
	/**
	 * The show time that the customer wants to book
	 */
	private MovieTime movieTime;

	/**
	 * Creates a {@code BookingController} object for the customer
	 * @param customer the customer who wants to do the booking
	 */
	public BookingController(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void begin() {		
		this.movieTime = selectMovieTime();
		
		if (movieTime.checkFull()) {
			IOController.displayMessage("Sorry, this show time is fully booked");
			NavigateControl.popOne();
			return;
		}
		
		BookingView.displaySeats(movieTime);
		
		int number = IOController.readInt("How many seats would you like to book: ");
		boolean[][] selectedSeats = BookingView.getSeats(number, movieTime);
		EnumMap<AgeGroup, Integer> ageGroupCount = BookingView.getAgeGroupCount(number);
		double totalPrice = calculatePrice(ageGroupCount);
		
		boolean confirm = IOController.readBoolean("Confirm booking (y/n): ", "y", "n");
		
		// ????
		if (confirm) {
			Booking booking = movieTime.createBooking(customer, selectedSeats, totalPrice);
			BookingView.displaySeats(movieTime);
			IOController.displayMessage("Booking successful!");
			IOController.displayMessage("Transaction ID: " + booking.getTransactionId());
			BookingView.printBookInfo(ageGroupCount, totalPrice);
			
		} else {
			IOController.displayMessage("Booking cancelled");
		}
		
		NavigateControl.popOne();
	}
	
	/**
	 * This method is used to control the flow of the show time selction. The customer is asked to select a cineplex, then a movie, then a timing.
	 * @return the selected  {@code MovieTime} object
	 */
	private MovieTime selectMovieTime() {
		// Select a cineplex
		List<Cineplex> cineplexList = DatabaseManager.getDataBase().getCineplexList();
		Cineplex cineplex = MenuView.getLabelledItem("Select a Cineplex", cineplexList);
	
		// Group the timing by movie
		List<MovieTime> movieTimeList = cineplex.getMovieTimes();
		Map<Movie, List<MovieTime>> movieTimesByMovie = movieTimeList.stream().collect(Collectors.groupingBy(MovieTime::getMovie));
		
		// Select a movie
		List<Movie> movieList = new ArrayList<Movie>();
		for (Movie movie: movieTimesByMovie.keySet()) {
			ShowStatus showStatus = movie.getShowStatus();
			if (showStatus == showStatus.PREVIEW || showStatus == showStatus.NOW_SHOWING)
				movieList.add(movie);
		}
		
		Movie movie = MenuView.getLabelledItem("Select a movie", movieList);
		
		// Select a movie show time
		List<MovieTime> movieShowTimeList = movieTimesByMovie.get(movie);
		Comparator<MovieTime> dateComparator = Comparator.comparing(MovieTime::getStartDateTime);
		movieShowTimeList.sort(dateComparator);
		MovieTime movieTime = MenuView.getLabelledItem("Select a Show Time", movieShowTimeList);
		
		return movieTime;
	}
	
	/**
	 * This method calculates the price of booking transaction
	 * @param ageGroupCount an {@code EnumMap} containing the number of tickets in each age group
	 * @return the total cost of the tickets
	 */
	public double calculatePrice(EnumMap<AgeGroup, Integer> ageGroupCount) {
		TicketPrice ticketPrice = DatabaseManager.getDataBase().getTicketPrice();
		
		double totalPrice = 0;
		
		// ??????????
		for (AgeGroup ageGroup : AgeGroup.values()) {
			totalPrice += ageGroupCount.get(ageGroup) * ticketPrice.getPrice(
				movieTime.getDate(),
				movieTime.getCinema().getCinemaClass(),
				ageGroup,
				movieTime.getMovie().getMovieType()
			);
		}
		return totalPrice;
	}
}
