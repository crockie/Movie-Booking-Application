package boundary;

import entity.AgeGroup;
import entity.DateGroup;
import entity.CinemaClass;
import entity.MovieType;
import entity.Ticket;
import entity.TicketPrice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * To edit the ticket price (Normal Price and Additional Price)
 */
public class TicketPriceEditView {
    /**
     * Edit the normal price of the ticket
     * 
     * @param ticketPrice Ticket Price database
     */
    public static void updateNormalPrice(TicketPrice ticketPrice) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Current normal price is: " + ticketPrice.getNormalPrice());
        double newNormalPrice = sc.nextDouble();
        ticketPrice.setNormalPrice(newNormalPrice);
    }

    /**
     * Edit the additional price of the ticket
     * 
     * @param ticketPrice Ticket Price database
     */
    public static void updateAdditionalPrice(TicketPrice ticketPrice) {
        System.out.println("Select the type to modify the additional price: ");
        System.out.println("1) Movie Type");
        System.out.println("2) Cinema class");
        System.out.println("1) Age of movie goer");
        System.out.println("1) Date Group");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                updateMovieAdditionalPrice(ticketPrice);
                break;
            case 2:
                updateCinemaAdditionalPrice(ticketPrice);
                break;
            case 3:
                updateAgeAdditionalPrice(ticketPrice);
                break;
            case 4:
                updateDateAdditionalPrice(ticketPrice);
                break;
        }
    }

    /**
     * Edit the additional price according to cinema class
     * 
     * @param ticketPrice Ticket Price database
     */
    public static void updateCinemaAdditionalPrice(TicketPrice ticketPrice) {
        CinemaClass cinemaClass = MenuView.getItemName("Please select the cinema class to be modified:",
                CinemaClass.values());

        double newCinemaClassPrice;
        Scanner sc = new Scanner(System.in);

        System.out.print("Input the new price:");
        newCinemaClassPrice = sc.nextDouble();

        ticketPrice.setCinemaClassPrice(cinemaClass, newCinemaClassPrice);
    }

    /**
     * Edit the additional price according to movie type
     * 
     * @param ticketPrice Ticket Price database
     */
    public static void updateMovieAdditionalPrice(TicketPrice ticketPrice) {
        MovieType movieType = MenuView.getItemName("Please select the movie type to be modified:",
                MovieType.values());

        double newMovieTypePrice;
        Scanner sc = new Scanner(System.in);

        System.out.print("Input the new price:");
        newMovieTypePrice = sc.nextDouble();

        ticketPrice.setMovieTypePrice(movieType, newMovieTypePrice);
    }

    /**
     * Edit the additional price according to age group
     * 
     * @param ticketPrice Ticket Price database
     */
    public static void updateAgeAdditionalPrice(TicketPrice ticketPrice) {
        AgeGroup ageGroup = MenuView.getItemName("Please select the cinema class to be modified:",
                AgeGroup.values());

        double newAgeGroupPrice;
        Scanner sc = new Scanner(System.in);

        System.out.print("Input the new price:");
        newAgeGroupPrice = sc.nextDouble();

        ticketPrice.setAgePrice(ageGroup, newAgeGroupPrice);
    }

    /**
     * Edit the additional price according to date group
     * 
     * @param ticketPrice Ticket Price database
     */
    public static void updateDateAdditionalPrice(TicketPrice ticketPrice) {
        DateGroup dateGroup = MenuView.getItemName("Please select the cinema class to be modified:",
                DateGroup.values());

        double newDateGroupPrice;
        Scanner sc = new Scanner(System.in);

        System.out.print("Input the new price:");
        newDateGroupPrice = sc.nextDouble();

        ticketPrice.setDatePrice(dateGroup, newDateGroupPrice);
    }

    /**
     * Add holiday to holiday array
     * 
     * @param ticketPrice Ticket Price database
     */
    public static void addHoliday(TicketPrice ticketPrice) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        LocalDate holidayDate;
        String date;

        while (true) {
            try {
                date = sc.nextLine();
                holidayDate = LocalDate.parse(date, dateTimeFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date format: dd/mm/yyyy");
            }
        }

        ticketPrice.getHoliday().add(holidayDate);
    }

    /**
     * Remove holiday from holiday array
     * 
     * @param ticketPrice Ticket Price database
     */
    public static void removeHoliday(TicketPrice ticketPrice) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(("dd/MM/yyyy"));
        ArrayList<LocalDate> holidayDates = ticketPrice.getHoliday();
        int size = holidayDates.size();

        String[] holidayDateString = new String[size];

        for (int i = 0; i < size; i++) {
            holidayDateString[i] = holidayDates.get(i).format(dateTimeFormatter);
        }

        int option = MenuView.getMenuOption("Please select the holiday date to be removed", holidayDateString);
        LocalDate dateSelected = holidayDates.get(option - 1);

        ticketPrice.getHoliday().remove(dateSelected);
    }
}
