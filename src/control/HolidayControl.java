package control;

import boundary.HolidayView;

/**
 * This class controls the display of the holidays.
 */
public class HolidayControl implements MainControl {
    /**
     * {@inheritDoc}
     */
    @Override
    public void begin() {
        HolidayView.displayHolidays();
        NavigateControl.popOne();
    }
}
