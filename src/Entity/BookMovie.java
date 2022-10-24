package Entity;

public interface BookMovie {
    public SeatStatus[][] getAvailableSeats();
    public boolean checkAvailability(boolean[][] selectedSeat);
    public boolean[][] getSeatLayout();
    public boolean checkFull();
}
