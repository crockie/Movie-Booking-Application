package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class contains all the information of a movie time
 */
public class MovieTime implements Serializable, BookMovie, ItemName {
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
    private static final long serialVersionUID = 5854487874565188L;

    /**
     * This method creates a {@code MovieTime} object for the given cinema, date and time and
     * movie
     * 
     * @param cinema        the cinema where the movie is shown
     * @param movieDateTime the date and time of the movie
     * @param movie         the movie to be shown
     */
    protected MovieTime(Cinema cinema, LocalDateTime movieDateTime, Movie movie) {
        this.cinema = cinema;
        this.movieDateTime = movieDateTime;
        this.movie = movie;
        movie.addMovieTime(this);
    }

    /**
     * This method creates a {@code Booking} object for the given customer, bookedSeats and price.
     * Add it to the list of bookings
     * 
     * @param customer    the customer who made the booking
     * @param bookedSeats the seats booked by the customer
     * @param price       the price of the booking
     * @return the newly created {@code Booking} object
     */
    public Booking createBooking(Customer customer, boolean[][] bookedSeats, double price) {
        Booking newBooking = new Booking(createtransactionID(), customer, bookedSeats, price);
        this.bookings.add(newBooking);
        return newBooking;
    }

    /**
     * This method creates the transaction ID of the booking
     * @return the transaction ID of the booking
     */
    public String createtransactionID() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String transaction = cinema.getCinemaCode() + LocalDateTime.now().format(format);
        return transaction;
    }

    /**
     * This method gets the total sales of the specific movie and time
     * @return double the total sales of the specific movie and time
     */
    public double getTotalSales() {
        double totalSales = 0;

        for (Booking booking : bookings) {
            totalSales += booking.getPrice();
        }

        return totalSales;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SeatStatus[][] getAvailableSeats() {
        boolean[][] seatLayout = cinema.getSeatLayout();
        SeatStatus[][] seatAvail = new SeatStatus[seatLayout.length][];

        for (int i = 0; i < seatLayout.length; i++) {
            boolean[] row = seatLayout[i];
            seatAvail[i] = new SeatStatus[row.length];

            for (int j = 0; j < row.length; j++) {
                if(row[j] == true) {
                    seatAvail[i][j] = SeatStatus.EMPTY;
                } else {
                    seatAvail[i][j] = SeatStatus.NO_SEAT;
                }
            }
        }

        for (Booking booking : bookings) {
            for (Ticket t : booking.getTickets()) {
                int row = t.getRow();
                int col = t.getColumn();

                if (seatAvail[row][col] == SeatStatus.EMPTY)
                    seatAvail[row][col] = SeatStatus.TAKEN;
            }
        }
        return seatAvail;
    }

    /**
     * This method counts the number of available seats in the specific movie and time
     * @return the number of available seats in the specific movie and time
     */
    @Override
    public int countAvailableSeats(){
        int availableSeats = 0;
        SeatStatus[][] seatAvail = this.getAvailableSeats();
        for (int i = 0; i < seatAvail.length; i++) {
            for (int j = 0; j < seatAvail[i].length; j++) {
                if (seatAvail[i][j] == SeatStatus.EMPTY) {
                    availableSeats++;
                }
            }
        }
        return availableSeats;
    }

    /**
     * This method gets the seat types of each seat of a specific movie and time
     * @return the seatTypes of each seat of a specific movie and time
     */

    @Override
    public SeatType[][] getSeatTypes() {
        boolean[][] seatLayout = cinema.getSeatLayout();
        SeatType[][] seatTypes = new SeatType[seatLayout.length][];

        for (int i = 0; i < seatLayout.length; i++) {
            boolean[] row = seatLayout[i];
            seatTypes[i] = new SeatType[row.length];

            for (int j = 0; j < row.length; j++) {

                if (i == seatLayout.length - 1 && (j < 2 || j > row.length - 3)) {
                    seatTypes[i][j] = SeatType.WHEELCHAIR;
                } else if (i > seatLayout.length / 3 * 2 || i == seatLayout.length - 1) {
                    seatTypes[i][j] = SeatType.COUPLE;
                } else {
                    seatTypes[i][j] = SeatType.NORMAL;
                }

            }
        }
        return seatTypes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkAvailability(boolean[][] selectedSeat) {
        SeatStatus[][] availableSeat = this.getAvailableSeats();

        for (int i = 0; i < availableSeat.length; i++) {
            for (int j = 0; j < availableSeat[i].length; j++) {
                SeatStatus seatStatus = availableSeat[i][j];

                if (selectedSeat[i][j] == true && (seatStatus == SeatStatus.NO_SEAT || seatStatus == SeatStatus.TAKEN))
                    return false;
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean[][] getSeatLayout() {
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
                if (availableSeat[i][j] == SeatStatus.EMPTY)
                    return false;
            }
        }

        return true;
    }

    /**
     * This method returns the movie date
     * 
     * @return the date of the movie
     */
    public LocalDate getDate() {
        return this.movieDateTime.toLocalDate();
    }

    /**
     * This method returns the movie date and time
     * 
     * @return the date and time of the movie
     */
    public LocalDateTime getMovieDateTime() {
        return this.movieDateTime;
    }

    /**
     * This method sets the movie date and time
     * 
     * @param movieDateTime the date and time of the movie
     */
    public void setMovieDateTime(LocalDateTime movieDateTime) {
        this.movieDateTime = movieDateTime;
    }

    /**
     * This method returns the movie
     * 
     * @return Movie the movie to be shown
     */
    public Movie getMovie() {
        return this.movie;
    }

    /**
     * This method sets the movie
     * 
     * @param movie the movie to be shown
     */
    public void setMovie(Movie movie) {
        this.movie.getMovieTimes().remove(this);
        this.movie = movie;
        movie.addMovieTime(this);
    }

    /**
     * This method returns the cinema
     * 
     * @return Cinema the cinema where the movie is shown
     */
    public Cinema getCinema() {
        return this.cinema;
    }

    /**
     * This method removes the specific movie and time from the movie and cinema
     */
    public void remove() {
        this.movie.getMovieTimes().remove(this);
        this.cinema.getMovieTimes().remove(this);
    }

    /**
     * This method gets all the bookings made for the movie
     * 
     * @return the list of bookings
     */
    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String nameToString() {
        return this.movie.nameToString() + " "
                + this.movieDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
