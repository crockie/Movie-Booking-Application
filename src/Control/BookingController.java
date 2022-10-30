//HAVENT CHANGED COMMENTS AND VARIABLES YET
package control;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

import entity.AgeGroup;
import entity.Booking;
import entity.Cineplex;
import entity.Customer;
import entity.DatabaseManager;
import entity.Movie;
import entity.MovieTime;
import entity.ShowStatus;
import entity.TicketPrice;
import view.BookingView;
import view.IOController;
import view.MenuView;

/**
 * This class handles the control flow of movie booking for a movie goer
 */
public class BookingController implements MainControl {
	/**
	 * The movie goer who wants to do the booking
	 */
	private Customer customer;
	/**
	 * The show time that the movie goer wants to book
	 */
	private MovieTime movieTime;

	/**
	 * Creates a new {@code BookingController} object for the given movie goer
	 * @param movieGoer the movie goer who wants to do the booking
	 */
	public BookingController(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void begin() {		
		this.movieTime = selectShowTime();
		
		if (movieTime.checkFull()) {
			IOController.displayMessage("Sorry, this show time is fully booked");
			NavigationController.goBack();
			return;
		}
		
		BookingView.displaySeats(movieTime);
		
		int number = IOController.readInt("How many seats would you like to book: ");
		boolean[][] selectedSeats = BookingView.getSeats(number, movieTime);
		EnumMap<AgeGroup, Integer> ageGroupCount = BookingView.getAgeGroupCount(number);
		double totalPrice = calculatePrice(ageGroupCount);
		
		boolean confirm = IOController.readBoolean("Confirm booking (y/n): ", "y", "n");
		
		if (confirm) {
			Booking booking = movieTime.createBooking(customer, selectedSeats, totalPrice);
			BookingView.displaySeats(movieTime);
			IOController.displayMessage("Booking successful!");
			IOController.displayMessage("Transaction ID: " + booking.getTransactionId());
			BookingView.printBookInfo(ageGroupCount, totalPrice);
			
		} else {
			IOController.displayMessage("Booking cancelled");
		}
		
		NavigationController.goBack();
	}
	
	/**
	 * This method is used to control the flow of the show time selction. The movie goer is asked to select a cineplex, then a movie, then a show time.
	 * @return the selected  {@code ShowTime} object
	 */
	private MovieTime selectShowTime() {
		// Select a cineplex
		List<Cineplex> cineplexList = DatabaseManager.getDataBase().getCineplexList();
		Cineplex cineplex = MenuView.getLabelledItem("Select a Cineplex", cineplexList);
	
		// Group show times by movie
		List<MovieTime> showTimeList = cineplex.getMovieTimes();
		Map<Movie, List<MovieTime>> showTimesByMovie = showTimeList.stream().collect(Collectors.groupingBy(MovieTime::getMovie));
		
		// Select a movie
		List<Movie> movieList = new ArrayList<Movie>();
		
		for (Movie movie: showTimesByMovie.keySet()) {
			ShowStatus showingStatus = movie.getShowStatus();
			if (showingStatus == showingStatus.PREVIEW || showingStatus == showingStatus.NOW_SHOWING)
				movieList.add(movie);
		}
		
		Movie movie = MenuView.getLabelledItem("Select a movie", movieList);
		
		// Select a show time
		List<MovieTime> movieShowTimeList = showTimesByMovie.get(movie);
		Comparator<MovieTime> dateComparator = Comparator.comparing(MovieTime::getStartDateTime);
		movieShowTimeList.sort(dateComparator);
		MovieTime showTime = MenuView.getLabelledItem("Select a Show Time", movieShowTimeList);
		
		return showTime;
	}
	
	/**
	 * This method calculates the price of booking transaction
	 * @param ageGroupCount an {@code EnumMap} containing the number of tickets for each age group
	 * @return the price of booking transaction
	 */
	public double calculatePrice(EnumMap<AgeGroup, Integer> ageGroupCount) {
		TicketPrice ticketPrice = DatabaseManager.getDataBase().getTicketPrice();
		
		double totalPrice = 0;
		
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
