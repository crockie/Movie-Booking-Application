package control;

import entity.DatabaseManager;
import entity.TicketPrice;
import view.MenuView;
import view.PricingSchemeEditView;

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
				"Edit modifiers",
				"Edit list of holidays",
				"Exit"
			);
			
			switch (option) {
				case 1:
					PricingSchemeEditView.updateNormalPrice(ticketPrice);
					break;
					
				case 2:
					PricingSchemeEditView.updateModifiers(ticketPrice);
					break;
					
				case 3:
					PricingSchemeEditView.updateHolidays(ticketPrice);
					break;
					
				case 4:
					NavigateControl.popOne();
					return;
			}
		}
	}	            		
}
