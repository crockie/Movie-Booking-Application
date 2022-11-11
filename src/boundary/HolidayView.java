package boundary;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.DatabaseManager;
/**
 * This class is the boundary class to display the holidays
 */
public class HolidayView {
    public static void displayHolidays() {
        ArrayList<LocalDate> holidays = DatabaseManager.getDataBase().getTicketPrice().getHoliday();
        System.out.println("Holidays: ");
        for (LocalDate holiday : holidays) {
            System.out.println(holiday);
        }
    }
}
