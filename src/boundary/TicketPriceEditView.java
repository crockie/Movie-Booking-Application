package boundary;

import entity.AgeGroup;
import entity.DateGroup;
import entity.CinemaClass;
import entity.MovieType;
import entity.TicketPrice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * This class edits the ticket price and holidays
 */
@SuppressWarnings("resource")
public class TicketPriceEditView {
    /**
     * This method edits the normal price of the ticket
     * 
     * @param ticketPrice Ticket Price in database
     */
    public static void updateNormalPrice(TicketPrice ticketPrice) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Current normal price is: " + ticketPrice.getNormalPrice());
            System.out.println("Input the new price: ");
            double newNormalPrice = sc.nextDouble();
            ticketPrice.setNormalPrice(newNormalPrice);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }

    }

    /**
     * This method is used to edit the additional price of the ticket
     * 
     * @param ticketPrice Ticket Price in database
     */
    public static void updateAdditionalPrice(TicketPrice ticketPrice) {
        System.out.println("Select the type to modify the additional price: ");
        System.out.println("1: Movie Type");
        System.out.println("2: Cinema class");
        System.out.println("3: Age of movie goer");
        System.out.println("4: Date Group");
        System.out.println("5: Couple Seat");
        System.out.print("Option: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice < 1 || choice > 5) {
            System.out.println("Invalid input. Please try again.");
            return;
        }
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
            case 5:
                updateCoupleSeatAdditionalPrice(ticketPrice);
                break;
        }
    }

    /**
     * This method edits the additional price according to cinema class
     * 
     * @param ticketPrice Ticket Price in database
     */
    public static void updateCinemaAdditionalPrice(TicketPrice ticketPrice) {
        CinemaClass cinemaClass = MenuView.getItemName("Please select the cinema class to be modified:",
                CinemaClass.values());

        double newCinemaClassPrice;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Input the new price:");
            newCinemaClassPrice = sc.nextDouble();

            ticketPrice.setCinemaClassPrice(cinemaClass, newCinemaClassPrice);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }

    }

    /**
     * This method edits the additional price according to movie type
     * 
     * @param ticketPrice Ticket Price in database
     */
    public static void updateMovieAdditionalPrice(TicketPrice ticketPrice) {
        MovieType movieType = MenuView.getItemName("Please select the movie type to be modified:",
                MovieType.values());

        double newMovieTypePrice;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Input the new price:");
            newMovieTypePrice = sc.nextDouble();

            ticketPrice.setMovieTypePrice(movieType, newMovieTypePrice);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    /**
     * This method edits the additional price according to age group
     * 
     * @param ticketPrice Ticket Price in database
     */
    public static void updateAgeAdditionalPrice(TicketPrice ticketPrice) {
        AgeGroup ageGroup = MenuView.getItemName("Please select the cinema class to be modified:",
                AgeGroup.values());

        double newAgeGroupPrice;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Input the new price:");
            newAgeGroupPrice = sc.nextDouble();

            ticketPrice.setAgePrice(ageGroup, newAgeGroupPrice);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    /**
     * This method edits the additional price according to date group
     * 
     * @param ticketPrice Ticket Price in database
     */
    public static void updateDateAdditionalPrice(TicketPrice ticketPrice) {
        DateGroup dateGroup = MenuView.getItemName("Please select the cinema class to be modified:",
                DateGroup.values());

        double newDateGroupPrice;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Input the new price:");
            newDateGroupPrice = sc.nextDouble();

            ticketPrice.setDatePrice(dateGroup, newDateGroupPrice);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }

    }

    /**
     * This method adds holiday to holiday array
     * 
     * @param ticketPrice Ticket Price in database
     */
    public static void addHoliday(TicketPrice ticketPrice) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Please enter a new holiday date dd/mm/yyyy: ");
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

        for (LocalDate holiday : ticketPrice.getHoliday()) {
            System.out.println(holiday);
        }
    }

    /**
     * This method removes holiday from holiday array
     * 
     * @param ticketPrice Ticket Price in database
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

    /**
     * This method edits the additional price according to age group
     * 
     * @param ticketPrice Ticket Price in database
     */
    public static void updateCoupleSeatAdditionalPrice(TicketPrice ticketPrice) {
        System.out.println("Please enter the additional price for couple seats: ");

        double newCoupleSeatPrice;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Input the new price:");
            newCoupleSeatPrice = sc.nextDouble();
            ticketPrice.setCoupleSeatPrice(newCoupleSeatPrice);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid price");
        }

    }
}
