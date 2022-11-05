package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class contains all the information of a movie time
 */
public class MovieTime implements Serializable, BookMovie, ItemName{
    /**
     * The cinema where the movie is shown
     */
    private Cinema cinema;
    /**
     * The date and time of the movie
     */
    private LocalDateTime movieDateTime;
    /**
     * The movie to be shown
     */
    private Movie movie;
    /**
     * The list of bookings for the movie time
     */
    private ArrayList<Booking> bookings = new ArrayList<Booking>();

    /**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = 55191665465118188L; //change serialisable

    /**
     * Creates a {@code MovieTime} object for the given cinema, date and time and movie
     * @param cinema the cinema where the movie is shown
     * @param movieDateTime the date and time of the movie
     * @param movie the movie to be shown
     */
    protected MovieTime(Cinema cinema, LocalDateTime movieDateTime, Movie movie){
        this.cinema = cinema;
        this.movieDateTime = movieDateTime;
        this.movie = movie;
        movie.addMovieTime(this);
    }
    
    /** 
     * Create {@code Booking} object for the given customer, bookedSeats and price. Add it to the list of bookings
     * @param customer the customer who made the booking
     * @param bookedSeats the seats booked by the customer
     * @param price the price of the booking
     * @return the newly created {@code Booking} object
     */
    public Booking createBooking(Customer customer, boolean[][] bookedSeats, double price ){
		Booking newBooking = new Booking(createTransactionId(), customer, bookedSeats, price);
		this.bookings.add(newBooking);
		return newBooking;
	}

    
    /** 
     * @return String the transaction id of the booking
     */
    public String createTransactionId() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		String transaction = cinema.getCinemaCode() + LocalDateTime.now().format(format);
		return transaction;
	}

    
    /** 
     * @return double the total sales of the movie time
     */
    public double getTotalSales(){
        double totalSales = 0;
        
        for(Booking booking: bookings){
            totalSales += booking.getPrice();
        }

        return totalSales;
    }
    
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public SeatStatus[][] getAvailableSeats(){
        boolean[][] seatLayout = cinema.getSeatLayout();
        SeatStatus[][] seatAvail = new SeatStatus[seatLayout.length][];

        for(int i = 0; i < seatLayout.length; i++){
            boolean[] row = seatLayout[i];
            seatAvail[i] = new SeatStatus[row.length];

            for(int j = 0; j < row.length; j++){
                seatAvail[i][j] = row[j] == true? SeatStatus.EMPTY:SeatStatus.NO_SEAT;
            }
        }

        for (Booking booking: bookings) {
			for (Ticket t: booking.getTickets()) {
				int row = t.getRow();
				int col = t.getColumn();
				
				if (seatAvail[row][col] == SeatStatus.EMPTY)
					seatAvail[row][col] = SeatStatus.TAKEN;
			}
		}
		return seatAvail;
    }     

    
    /** 
     * {@inheritDoc}
     */
    @Override
	public boolean checkAvailability(boolean [][] selectedSeat) {
		SeatStatus[][] availableSeat = this.getAvailableSeats();
		
		for (int i = 0; i < availableSeat.length; i++) {
			for (int j = 0; j < availableSeat[i].length; j++) {
				SeatStatus seatStatus = availableSeat[i][j];
				
				if (selectedSeat[i][j] == true && (seatStatus == SeatStatus.TAKEN || seatStatus == SeatStatus.NO_SEAT))
					return false;
			}
		}
	
		return true;
	}

    
    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean[][] getSeatLayout(){
        return cinema.getSeatLayout();
    }

    
    /** 
     * {@inheritDoc}
     */
    @Override
	public boolean checkFull() {
		SeatStatus[][] availableSeat = this.getAvailableSeats();

		for (int i = 0; i < availableSeat.length; i++) {
			for (int j = 0; j < availableSeat[i].length; j++) {
				if(availableSeat[i][j] == SeatStatus.EMPTY)
					return false;
			}
		}
		
		return true;
	}
    
    /** 
     * This method returns the movie date
     * @return LocalDate the date of the movie
     */
    public LocalDate getDate(){
        return this.movieDateTime.toLocalDate();
    }

    
    /** 
     * This method returns the movie date and time
     * @return LocalDateTime the date and time of the movie
     */
    public LocalDateTime getMovieDateTime(){
        return this.movieDateTime;
    }
    
    /** 
     * This method sets the movie date and time
     * @param movieDateTime the date and time of the movie
     */
    public void setMovieDateTime(LocalDateTime movieDateTime) {
		this.movieDateTime = movieDateTime;
	}

    
    /** 
     * This method returns the movie
     * @return Movie the movie to be shown
     */
    public Movie getMovie(){
        return this.movie;
    }
    
    /** 
     * This method sets the movie
     * @param movie the movie to be shown
     */
    public void setMovie(Movie movie){
        this.movie.getMovieTimes().remove(this);
        this.movie = movie;
        movie.addMovieTime(this);
    }

    /** 
     * This method returns the cinema
     * @return Cinema the cinema where the movie is shown
     */
    public Cinema getCinema() {
        return this.cinema;
    }


    /** 
     * This method remove the movie time from the movie and cinema
     */
    public void remove() {
		this.movie.getMovieTimes().remove(this);
		this.cinema.getMovieTimes().remove(this);
	}

    
    /** 
     * This method returns the bookings made for the movie
     * @return ArrayList<Booking> the list of bookings
     */
    public ArrayList<Booking> getBookings(){
        return this.bookings;
    }

    
    /** 
     * {@inheritDoc}
     */
    @Override
    public String nameToString(){
        return this.movie.nameToString() + " " + this.movieDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
