package Entity;

public class Ticket{
	private int column;
	private int row;
	
	protected Ticket(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getColumn() {
		return column;
	}
	public int getRow() {
		return row;
	}
}
