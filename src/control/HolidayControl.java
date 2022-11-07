package control;

import boundary.HolidayView;

public class HolidayControl implements MainControl {
    @Override
    public void begin() {
        HolidayView.displayHolidays();
        NavigateControl.popOne();
    }
}
