package boundary;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.DatabaseManager;

/**
 * This class displays the holidays
 */
public class HolidayView {
    /**
     * This method displays the holidays
     */
    public static void displayHolidays() {
        ArrayList<LocalDate> holidays = DatabaseManager.getDataBase().getTicketPrice().getHoliday();
        System.out.println("Holidays: ");
        for (LocalDate holiday : holidays) {
            System.out.println(holiday);
        }
    }
}
