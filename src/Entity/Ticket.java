package Entity;

public class Ticket{
	private int col;
	private int row;
	
	protected Ticket(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getCol() {
		return col;
	}
	public int getRow() {
		return row;
	}
}