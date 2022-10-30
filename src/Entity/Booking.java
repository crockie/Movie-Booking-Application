package Entity;
import java.util.ArrayList;

public class Booking {
	private String transactionId; 
	private Customer customer;
	private ArrayList<Ticket> tickets;
	private double price;
	
	// Creates a Booking object with transaction id, user, selected seats and price
	public Booking(String transactionId, Customer customer, boolean[][] selectedSeats, double price) {
		this.transactionId = transactionId;
		this.customer = customer;
		this.price = price;
		this.tickets = new ArrayList<Ticket>();
		for (int i = 0; i < selectedSeats.length ; i++){
			for(int j = 0; j < selectedSeats[i].length; j++){
				if(selectedSeats[i][j] == true){
					Ticket ticket = new Ticket(i, j);
					tickets.add(ticket);
				}
			}
		}
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	
	public Customer getUser() {
		return customer;
	}
	
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public double getPrice() {
		return price;
	}	
}
