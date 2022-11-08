//HAVENT CHANGED COMMENTS AND VARIABLES YET
package control;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import boundary.BookingView;
import boundary.MenuView;
import boundary.TicketPriceView;

import entity.*;

/**
 * This class handles the control flow of movie booking for a customer
 */
public class BookingControl implements MainControl {
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
	 * 
	 * @param customer the customer who wants to do the booking
	 */
	public BookingControl(Customer customer) {
		this.customer = customer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void begin() {
		this.movieTime = selectMovieTime();
		TicketPriceView.displayTicketPriceToCustomer(movieTime);

		if (movieTime.checkFull()) {
			System.out.println("Sorry, this show time is fully booked");
			NavigateControl.popOne();
			return;
		}

		BookingView.displaySeats(movieTime, null);

		int number;

		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.println("How many seats would you like to book: ");
				number = sc.nextInt();
				sc.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid number");
				continue;
			}
		}

		boolean[][] selectedSeats = BookingView.getSeats(number, movieTime);

		SeatType[][] seatTypes = movieTime.getSeatTypes();
		int noOfCoupleSeats = 0;
		for (int i = 0; i < selectedSeats.length; i++) {
			for (int j = 0; j < selectedSeats[i].length; j++)
				if (selectedSeats[i][j] == true && seatTypes[i][j] == SeatType.COUPLE) {
					noOfCoupleSeats++;
					j++;
				}
		}

		EnumMap<AgeGroup, Integer> ageGroupCount = BookingView.getAgeGroupCount(number + noOfCoupleSeats);
		double totalPrice = calculatePrice(ageGroupCount, noOfCoupleSeats);

		boolean confirm;

		BookingView.displaySeats(movieTime, selectedSeats);
		BookingView.displayPrice(movieTime);

		System.out.println("");
		System.out.println("Total Price: $" + String.format("%.2f", totalPrice));

		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.println("Confirm booking (y/n): ");
				String input = sc.nextLine();

				if (input.equals("y")) {
					confirm = true;
					break;
				} else if (input.equals("n")) {
					confirm = false;
					break;
				} else {
					System.out.println("Please enter a valid input");
					continue;
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid input");
				continue;
			}

		}

		if (confirm) {
			Booking booking = movieTime.createBooking(customer, selectedSeats, totalPrice);
			BookingView.displaySeats(movieTime, null);
			System.out.println("Booking successful!");
			System.out.println("Transaction ID: " + booking.getTransactionID());
			BookingView.printBookInfo(movieTime, ageGroupCount, totalPrice);

		} else {
			System.out.println("Booking cancelled");

		}

		NavigateControl.popOne();
	}

	/**
	 * This method is used to control the flow of the show time selction. The
	 * customer is asked to select a cineplex, then a movie, then a timing.
	 * 
	 * @return the selected {@code MovieTime} object
	 */
	private MovieTime selectMovieTime() {
		// Select a cineplex
		List<Cineplex> cineplexList = DatabaseManager.getDataBase().getCineplexList();
		Cineplex cineplex = MenuView.getItemName("Select a Cineplex", cineplexList);

		// Group the timing by movie
		List<MovieTime> movieTimeList = cineplex.getMovieTimes();
		Map<Movie, List<MovieTime>> movieTimesByMovie = movieTimeList.stream()
				.collect(Collectors.groupingBy(MovieTime::getMovie));

		// Select a movie
		List<Movie> movieList = new ArrayList<Movie>();
		for (Movie m : movieTimesByMovie.keySet()) {
			ShowStatus showStatus = m.getShowStatus();
			if (showStatus == ShowStatus.PREVIEW || showStatus == ShowStatus.NOW_SHOWING) {
				movieList.add(m);
			}
		}

		Movie movie = MenuView.getItemName("Select a movie", movieList);

		// Select a movie show time
		List<MovieTime> movieShowTimeList = movieTimesByMovie.get(movie);
		Comparator<MovieTime> dateComparator = Comparator.comparing(MovieTime::getMovieDateTime);
		movieShowTimeList.sort(dateComparator);
		MovieTime movieTime = MenuView.getItemName("Select a Show Time", movieShowTimeList);

		return movieTime;
	}

	/**
	 * This method calculates the price of booking transaction
	 * 
	 * @param ageGroupCount an {@code EnumMap} containing the number of tickets in
	 *                      each age group
	 * @return the total cost of the tickets
	 */
	public double calculatePrice(EnumMap<AgeGroup, Integer> ageGroupCount, int noOfCoupleSeats) {
		TicketPrice ticketPrice = DatabaseManager.getDataBase().getTicketPrice();

		double totalPrice = 0;

		for (AgeGroup ageGroup : AgeGroup.values()) {
			totalPrice += ageGroupCount.get(ageGroup) * ticketPrice.getTicketPrice(
					movieTime.getDate(),
					movieTime.getCinema().getCinemaClass(),
					ageGroup,
					movieTime.getMovie().getMovieType());
		}
		if (noOfCoupleSeats > 0) {
			totalPrice += noOfCoupleSeats * ticketPrice.getCoupleSeatPrice();
		}

		return totalPrice;
	}
}
