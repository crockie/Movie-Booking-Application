package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MovieTime implements BookMovie{
    private Cinema cinema;
    private LocalDateTime movieDateTime;
    private Movie movie;
    private ArrayList<Booking> bookings = new ArrayList<Booking>();

    protected MovieTime(Cinema cinema, LocalDateTime movieDateTime, Movie movie){
        this.cinema = cinema;
        this.movieDateTime = movieDateTime;
        this.movie = movie;
        movie.addMovieTime(this);
    }


    public Booking createBooking(User user, boolean[][] bookedSeats, double price ){
		Booking newBooking = new Booking(createTransactionId(), user, bookedSeats, price);
		this.bookings.add(newBooking);
		return newBooking;
	}

    public String createTransactionId() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		String transaction = cinema.getName() + LocalDateTime.now().format(format);
		return transaction;
	}

    public double getTotalSales(){
        double totalSales = 0;
        
        for(Booking booking: bookings){
            totalSales += booking.getPrice();
        }

        return totalSales;
    }
    
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
				int col = t.getCol();
				
				if (seatAvail[row][col] == SeatStatus.EMPTY)
					seatAvail[row][col] = SeatStatus.TAKEN;
			}
		}
		return seatAvail;
    }     

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

    @Override
    public boolean[][] getSeatLayout(){
        return cinema.getSeatLayout();
    }

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

    /*implement print method
    public String getName(){
        return this.getName();
    }*/

    public LocalDate getDate(){
        return this.movieDateTime.toLocalDate();
    }

    public LocalDateTime getMovieDateTime(){
        return this.movieDateTime;
    }
    public void setMovieDateTime(LocalDateTime movieDateTime) {
		this.movieDateTime = movieDateTime;
	}

    public Movie getMovie(){
        return this.movie;
    }
    public void setMovie(Movie movie){
        this.movie.getMovieTimes().remove(this);
        this.movie = movie;
        movie.addMovieTime(this);
    }
    public void remove() {
		this.movie.getMovieTimes().remove(this);
		this.cinema.getMovieTimes().remove(this);
	}

    public ArrayList<Booking> getBookings(){
        return this.bookings;
    }
}
