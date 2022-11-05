package control;

import entity.DatabaseManager;
import entity.TicketPrice;
import boundary.MenuView;
import boundary.TicketPriceEditView;

/**
 * This class controls the change of the ticket prices and the list of holidays.
 */
public class TicketPriceLogicControl implements MainControl {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void begin() {
		
		TicketPrice ticketPrice = DatabaseManager.getDataBase().getTicketPrice();
		
		while (true) {
			int option = MenuView.getMenuOption(
				"Enter your choice: ",
				"Edit normal price",
				"Edit additional price",
				"Add holidays",
				"Remove holidays",
				"Exit"
			);
			
			switch (option) {
				case 1:
					TicketPriceEditView.updateNormalPrice(ticketPrice);
					break;
					
				case 2:
					TicketPriceEditView.updateAdditionalPrice(ticketPrice);
					break;
					
				case 3:
					TicketPriceEditView.addHoliday(ticketPrice);
					break;
				
				case 4:
					TicketPriceEditView.removeHoliday(ticketPrice);
					break;

				case 5:
					NavigateControl.popOne();
					return;
			}
		}
	}
}
