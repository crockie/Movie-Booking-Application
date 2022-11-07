package boundary;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.DatabaseManager;

public class HolidayView {
    public static void displayHolidays() {
        ArrayList<LocalDate> holidays = DatabaseManager.getDataBase().getTicketPrice().getHoliday();
        System.out.println("Holidays: ");
        for (LocalDate holiday : holidays) {
            System.out.println(holiday);
        }
    }
}
