package boundary;

import entity.AgeGroup;
import entity.BookMovie;
import entity.CinemaClass;
import entity.DatabaseManager;
import entity.MovieTime;
import entity.MovieType;
import entity.SeatStatus;
import entity.SeatType;
import entity.TicketPrice;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.util.EnumMap;

/**
 * This class handles the display of the booking information and the retrieval
 * of details from the customer
 */
@SuppressWarnings("resource")
public class BookingView {

    /**
     * This method gets all the seats the movie goer wants to book
     * 
     * @param num      the total number of tickets to be booked
     * @param showTime the selected show time
     * @return a 2D boolean array representing the selected seats. The value of the
     *         selected seat is true, else it's false.
     */
    public static boolean[][] getSeats(int num, BookMovie showTime) {
        boolean[][] seatLayout = showTime.getSeatLayout();
        boolean[][] selectedSeat = new boolean[seatLayout.length][];
        SeatType[][] seatTypes = showTime.getSeatTypes();
        Scanner sc = new Scanner(System.in);

        while (true) {
            // Create all 2D boolean array of false with the same dimensions as seatLayout
            for (int i = 0; i < seatLayout.length; i++) {
                selectedSeat[i] = new boolean[seatLayout[i].length];

                for (int j = 0; j < seatLayout[i].length; j++)
                    selectedSeat[i][j] = false;
            }

            int a = 1;

            for (int i = 0; i < num; i++) {
                while (true) {
                    try {
                        System.out.printf("Please enter the #%d seat number (eg. G6): ", a);
                        String input = sc.nextLine().toUpperCase();
                        int row = (input.charAt(0)) - 'A';
                        int col = Integer.parseInt(input.substring(1));
                        if (seatTypes[row][col - 1] == SeatType.COUPLE
                                || seatTypes[row][col - 1] == SeatType.WHEELCHAIR) {
                            if (((col < 11 && (col - 1) % 2 == 0)) || (col > 11 && (col - 1) % 2 == 1)) {
                                selectedSeat[row][col - 1] = true;
                                selectedSeat[row][col] = true;
                            } else {
                                selectedSeat[row][col - 1] = true;
                                selectedSeat[row][col - 2] = true;
                            }

                        } else if (selectedSeat[row][col - 1] == true){
                            System.out.println("Seat " + input.toUpperCase() + " has already been selected, choose a different seat.");
                            continue;
                        } else {
                            selectedSeat[row][col - 1] = true;
                        }
                        a++;
                        break;
                    } catch (Exception e) {
                        System.out.println("Please enter the correct seat number!");
                    }
                }
            }

            if (showTime.checkAvailability(selectedSeat)) {
                break;

            } else {
                System.out.println("Seats selected are unavailable.");
                System.out.println("Please choose another seat.");
            }
        }

        return selectedSeat;
    }

    /**
     * This method gets the number of tickets for each age group
     * 
     * @param num the total number of tickets to be booked
     * @return the number of tickets for each age group
     */
    public static EnumMap<AgeGroup, Integer> getAgeGroupCount(int num) {
        System.out.println("How many tickets for each age group?");

        EnumMap<AgeGroup, Integer> ageGroupCount = new EnumMap<AgeGroup, Integer>(AgeGroup.class);

        while (true) {
            int totalCount = 0;

            for (AgeGroup ageGroup : AgeGroup.values()) {

                while (true) {
                    try {
                        System.out.print(ageGroup.nameToString() + ": ");
                        Scanner sc = new Scanner(System.in);
                        int count = sc.nextInt();
                        totalCount += count;
                        ageGroupCount.put(ageGroup, count);
                        break;

                    } catch (InputMismatchException e) {
                        System.out.println("Please enter an integer");
                    }
                }
            }

            if (totalCount == num)
                break;
            else
                System.out.println("Error! Total doesn't add up to " + num);
        }

        return ageGroupCount;
    }

    /**
     * This method displays the seating plan for the selected show time
     * 
     * @param showTime     the selected show time to display the seats of
     * @param selectedSeat The seats selected by customer
     */
    public static void displaySeats(BookMovie showTime, boolean[][] selectedSeat) {
        SeatStatus[][] availSeats = showTime.getAvailableSeats();
        SeatType[][] seatTypes = showTime.getSeatTypes();
        int desiredWidth = availSeats[0].length * 5 + 4;

        // Create Line String
        String line = "";
        for (int i = 0; i < desiredWidth; i++)
            line += "-";

        // Create column headers
        String columnHeaders = "  ";

        for (int i = 0; i < availSeats[0].length; i++) {
            if (i < 10)
                columnHeaders += "  " + (i + 1) + "  ";
            else
                columnHeaders += " " + (i + 1) + "  ";
        }

        if (selectedSeat != null) {
            System.out.println("Preview:");
        }

        String margin = "";
        for (int i = 0; i < (desiredWidth - "SCREEN".length()) / 2; i++)
            margin += " ";
        System.out.println(margin + "SCREEN");

        System.out.println(line);
        System.out.println(columnHeaders);
        System.out.println(line);

        // Print rows of seats
        char row = 'A';

        for (int i = 0; i < seatTypes.length; i++) {
            String rowString = "";
            rowString += row + " ";
            for (int j = 0; j < seatTypes[0].length; j++) {
                if (seatTypes[i][j] == SeatType.WHEELCHAIR) {
                    if (availSeats[i][j] == SeatStatus.EMPTY) {
                        String color = Colours.ANSI_PURPLE;
                        if (selectedSeat != null)
                            color = selectedSeat[i][j] ? Colours.ANSI_YELLOW : Colours.ANSI_PURPLE;
                        rowString += color + "[        ]" + Colours.ANSI_RESET;
                        j++;
                    } else if (availSeats[i][j] == SeatStatus.TAKEN) {
                        rowString += Colours.ANSI_PURPLE + "[   x    ]" + Colours.ANSI_RESET;
                        j++;
                    } else {
                        rowString += "     ";
                    }
                } else if (seatTypes[i][j] == SeatType.COUPLE) {
                    if (availSeats[i][j] == SeatStatus.EMPTY) {
                        String color = Colours.ANSI_BLUE;
                        if (selectedSeat != null)
                            color = selectedSeat[i][j] ? Colours.ANSI_YELLOW : Colours.ANSI_BLUE;
                        rowString += color + "[        ]" + Colours.ANSI_RESET;
                        j++;
                    } else if (availSeats[i][j] == SeatStatus.TAKEN) {
                        rowString += Colours.ANSI_BLUE + "[   x    ]" + Colours.ANSI_RESET;
                        j++;
                    } else {
                        rowString += "     ";
                    }
                } else if (seatTypes[i][j] == SeatType.NORMAL) {
                    if (availSeats[i][j] == SeatStatus.EMPTY) {
                        String color = Colours.ANSI_GREEN;
                        if (selectedSeat != null)
                            color = selectedSeat[i][j] ? Colours.ANSI_YELLOW : Colours.ANSI_GREEN;
                        rowString += color + "[   ]" + Colours.ANSI_RESET;
                    } else if (availSeats[i][j] == SeatStatus.TAKEN)
                        rowString += Colours.ANSI_GREEN + "[ x ]" + Colours.ANSI_RESET;
                    else
                        rowString += "     ";
                }
            }
            rowString += " " + row;
            System.out.println(rowString);
            row++;
        }

        System.out.println(line);
        System.out.println(columnHeaders);
        System.out.println(line);

        String margin2 = "";
        for (int i = 0; i < (desiredWidth - "ENTRANCE".length()) / 2; i++)
            margin2 += " ";
        System.out.println(margin2 + "ENTRANCE");

        System.out.println("Legend: ");
        System.out.println(Colours.ANSI_GREEN + "[   ]" + Colours.ANSI_RESET + " Normal Seat");
        System.out.println(Colours.ANSI_BLUE + "[   ]" + Colours.ANSI_RESET + " Couple Seat");
        System.out.println(Colours.ANSI_PURPLE + "[   ]" + Colours.ANSI_RESET + " Wheelchair Seat");
        if (selectedSeat != null)
            System.out.println(Colours.ANSI_YELLOW + "[   ]" + Colours.ANSI_RESET + " Selected Seat");

        System.out.println("To continue, please press enter.");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    /**
     * This method displays price once customer selects the seat
     * 
     * @param movieTime Movie details
     */
    public static void displayPrice(MovieTime movieTime) {
        System.out.println("Ticket Price:");
        TicketPrice ticketPrice = DatabaseManager.getDataBase().getTicketPrice();

        double price;
        CinemaClass cinemaClass = movieTime.getCinema().getCinemaClass();
        LocalDate date = movieTime.getDate();
        MovieType movieType = movieTime.getMovie().getMovieType();

        if (ticketPrice.isHoliday(date)) {
            System.out.println(date + " is holiday");
        }

        for (AgeGroup ageGroup : AgeGroup.values()) {
            price = ticketPrice.getTicketPrice(date, cinemaClass, ageGroup, movieType);
            System.out.println(ageGroup.nameToString() + ": SGD"
                    + price + " per pax");
        }
        System.out.println("Couple: Additional SGD" + ticketPrice.getCoupleSeatPrice() + " per seat");
    }

    /**
     * This method displays the booking information
     * 
     * @param movieTime     the movie time of the selected movie
     * @param ageGroupCount the number of tickets for each age group
     * @param totalPrice    the total price of the booking
     */
    public static void printBookInfo(MovieTime movieTime, EnumMap<AgeGroup, Integer> ageGroupCount, double totalPrice) {
        System.out.println("Booking Information");
        String line = "";
        for (int i = 0; i < "Booking Information".length(); i++)
            line += "-";
        System.out.println(line);

        System.out.println("Cinema: " + movieTime.getCinema().nameToString());
        System.out.println("Movie & Showtime: " + movieTime.nameToString());

        for (AgeGroup ageGroup : AgeGroup.values())
            System.out.println(ageGroup.nameToString() + ": " + ageGroupCount.get(ageGroup));

        System.out.println("");
        System.out.println("Total Price: $" + String.format("%.2f", totalPrice));

        System.out.println("To continue, please press enter.");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

}
