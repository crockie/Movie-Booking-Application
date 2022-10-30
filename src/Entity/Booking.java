package Entity;
import java.util.ArrayList;

public class Booking {
	private String transactionId; 
	private User user;
	private ArrayList<Ticket> tickets;
	private double price;
	
	// Creates a Booking object with transaction id, user, selected seats and price
	public Booking(String transactionId, User user, boolean[][] selectedSeats, double price) {
		this.transactionId = transactionId;
		this.user = user;
		this.price = price;
		this.tickets = new ArrayList<Ticket>();
		for (int i = 0; i < selectedSeats.length ; i++){
			for(int j = 0; j < selectedSeats[i].length; j++){
				if(bookedSeats[i][j] == true){
					Ticket ticket = new Ticket(i, j);
					tickets.add(ticket);
				}
			}
		}
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	
	public User getUser() {
		return user;
	}
	
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public double getPrice() {
		return price;
	}	
}
