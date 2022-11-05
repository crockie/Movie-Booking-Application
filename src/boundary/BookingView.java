package boundary;

import entity.AgeGroup;
import entity.BookMovie;
import entity.SeatStatus;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.EnumMap;

/**
 * This class handles the display of the booking information and the retrieval of details from the movie goer
 */
public class BookingView {
    /**
     * This method gets all the seats the movie goer wants to book
     * @param n the total number of tickets to be booked
     * @param showTime the selected show time
     * @return a 2D boolean array representing the selected seats. If the seat is selected, the value is true, otherwise it's false.
     */
    public static boolean[][] getSeats(int n, BookMovie showTime) {
        boolean[][] seatLayout = showTime.getSeatLayout();
        boolean[][] selectedSeat = new boolean[seatLayout.length][];

        while (true) {
            // Create all 2D boolean array of false with the same dimensions as seatLayout
            for (int i = 0; i < seatLayout.length; i++) {
                selectedSeat[i] = new boolean[seatLayout[i].length];

                for (int j = 0; j < seatLayout[i].length; j++)
                    selectedSeat[i][j] = false;
            }

            System.out.println("Enter the seat no.s (e.g. A1): ");

            for (int i = 0; i < n; i++) {
                System.out.print("");
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();

                int row = input.charAt(0) - 'A';
                int col = Integer.parseInt(input.substring(1));
                selectedSeat[row][col - 1] = true;
            }

            if (showTime.checkAvailability(selectedSeat)) {
                break;

            } else {
                System.out.println("Unavailable seats selected");
                System.out.println("Select seats again");
            }
        }

        return selectedSeat;
    }

    /**
     * This method gets the number of tickets for each age group
     * @param n the total number of tickets to be booked
     * @return the number of tickets for each age group
     */
    public static EnumMap<AgeGroup, Integer> getAgeGroupCount(int n) {
        System.out.println("How many of each age group?");

        EnumMap<AgeGroup, Integer> ageGroupCount = new EnumMap<AgeGroup, Integer>(AgeGroup.class);

        while (true) {
            int totalCount = 0;

            for (AgeGroup ageGroup: AgeGroup.values()) {

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

            if (totalCount == n)
                break;
            else
                System.out.println("Error! Total doesn't add up to " + n);
        }

        return ageGroupCount;
    }

    /**
     * This method displays the seating plan for the selected show time
     * @param showTime the selected show time to display the seats of
     */
    public static void displaySeats(BookMovie showTime) {
        SeatStatus[][] availSeats = showTime.getAvailableSeats();
        int textWidth = availSeats[0].length * 5 + 4;

        // Create Line String
        String line = "";
        for (int i =0; i< textWidth; i++)
            line += "-";

        // Create column headers
        String columnHeaders = "  ";

        for (int i = 0; i < availSeats[0].length; i++) {
            if (i < 10)
                columnHeaders += "  " + (i + 1) + "  ";
            else
                columnHeaders += " " + (i + 1) + "  ";
        }

        String margin = "";
        for (int i = 0; i < (textWidth - "SCREEN".length()) / 2; i++)
            margin += " ";
        System.out.println(margin + "SCREEN");

        System.out.println(line);
        System.out.println(columnHeaders);
        System.out.println(line);

        // Print rows of seats
        char row = 'A';

        for (SeatStatus[] availSeatRow: availSeats) {
            String rowString = "";
            rowString += row + " ";

            for (SeatStatus seatStatus: availSeatRow) {
                switch (seatStatus) {
                    case TAKEN:
                        rowString += "[ x ]";
                        break;

                    case EMPTY:
                        rowString += "[   ]";
                        break;

                    case NO_SEAT:
                        rowString += "     ";
                        break;
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
        for (int i = 0; i < (textWidth - "ENTRANCE".length()) / 2; i++)
            margin2 += " ";
        System.out.println(margin2 + "ENTRANCE");

        System.out.println("Press enter to continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    /**
     * This method displays the booking information
     * @param ageGroupCount the number of tickets for each age group
     * @param totalPrice the total price of the booking
     */
    public static void printBookInfo(EnumMap<AgeGroup, Integer> ageGroupCount, double totalPrice) {
        System.out.println("Booking Information");
        String line = "";
        for (int i = 0; i < "Booking Information".length(); i++)
            line += "-";
        System.out.println(line);

        for (AgeGroup ageGroup: AgeGroup.values())
            System.out.println(ageGroup.nameToString() + ": " + ageGroupCount.get(ageGroup));

        System.out.println("");
        System.out.println("Total Price: $" + String.format("%.2f", totalPrice));

        System.out.println("Press enter to continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}

