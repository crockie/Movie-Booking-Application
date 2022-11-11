package control;

import boundary.TicketPriceView;
/**
 * This class controls the ticket price
 */
public class TicketPriceControl implements MainControl {
    @Override
    public void begin() {
        TicketPriceView.displayAllTicketPrice();
    }
}
