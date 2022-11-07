package control;

import boundary.TicketPriceView;

public class TicketPriceControl implements MainControl {
    @Override
    public void begin() {
        TicketPriceView.displayAllTicketPrice();
    }
}
