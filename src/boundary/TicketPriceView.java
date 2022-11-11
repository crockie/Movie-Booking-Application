package boundary;

import entity.AgeGroup;
import entity.CinemaClass;
import entity.DatabaseManager;
import entity.DateGroup;
import entity.MovieTime;
import entity.MovieType;
import entity.TicketPrice;

import control.NavigateControl;
/**
 * This class is the boundary class to display the ticket price
 */
public class TicketPriceView {
    /**
     * This method displays all the ticket prices
     */
    public static void displayAllTicketPrice() {
        TicketPrice ticketPrice = DatabaseManager.getDataBase().getTicketPrice();
        double price;

        System.out.println("Ticket Price:");
        System.out.println("Normal Price: SGD" + ticketPrice.getNormalPrice());
        System.out.println();

        System.out.println("Additional Price:");
        System.out.println("  Movie Type:");
        for (MovieType movieType : MovieType.values()) {
            price = ticketPrice.getMovieTypePrice(movieType);
            System.out.println("\t" + movieType.nameToString() + ": SGD "
                    + price);
        }
        System.out.println();
        System.out.println("  Cinema Class:");
        for (CinemaClass cinemaClass : CinemaClass.values()) {
            price = ticketPrice.getCinemaClassPrice(cinemaClass);
            System.out.println("\t" + cinemaClass.nameToString() + ": SGD "
                    + price);
        }
        System.out.println();
        System.out.println("  Age Group:");
        for (AgeGroup ageGroup : AgeGroup.values()) {
            price = ticketPrice.getAgePrice(ageGroup);
            System.out.println("\t" + ageGroup.nameToString() + ": SGD "
                    + price);
        }
        System.out.println();
        System.out.println("  Date Group:");
        for (DateGroup dateGroup : DateGroup.values()) {
            price = ticketPrice.getDatePrice(dateGroup);
            System.out.println("\t" + dateGroup.nameToString() + ": SGD "
                    + price);
        }
        System.out.println();
        System.out.println("  Couple seat: SGD " + ticketPrice.getCoupleSeatPrice());

        NavigateControl.popOne();
    }
    /**
     * This method displays the ticket price for a particular showtime
     * @param movieTime The showtime
     */
    public static void displayTicketPriceToCustomer(MovieTime movieTime) {
        TicketPrice ticketPrice = DatabaseManager.getDataBase().getTicketPrice();
        double price = ticketPrice.getNormalPrice();

        System.out.println("Current ticket prices");

        CinemaClass cinemaClass = movieTime.getCinema().getCinemaClass();
        MovieType movieType = movieTime.getMovie().getMovieType();

        System.out.println(cinemaClass + " class");
        System.out.println(movieType + " type\n");
        price += ticketPrice.getCinemaClassPrice(cinemaClass) + ticketPrice.getMovieTypePrice(movieType);

        int i = 0;
        System.out.println("Ticket Type" + "\t" + "Price");
        System.out.println("---------------------------------------");
        for (AgeGroup ageGroup : AgeGroup.values()) {
            price += ticketPrice.getAgePrice(ageGroup);
            System.out.print(ageGroup.nameToString() + ":");
            for (DateGroup dateGroup : DateGroup.values()) {
                price += ticketPrice.getDatePrice(dateGroup);
                if (i == 0) {
                    System.out.println("\t" + dateGroup.nameToString() + "\tSGD " + price);
                    i++;
                    price -= ticketPrice.getDatePrice(dateGroup);
                    continue;
                }
                System.out.println("\t\t" + dateGroup.nameToString() + "\tSGD " + price);
                price -= ticketPrice.getDatePrice(dateGroup);
            }
            price -= ticketPrice.getAgePrice(ageGroup);
        }
        i--;
        System.out.print("Couple Seat:");
        System.out.println("\tAdditional SGD " + ticketPrice.getCoupleSeatPrice());

    }
}
