package control;

import java.util.Scanner;

import entity.DatabaseManager;
/**
 * This class controls the display of the top 5 movies by:
 * 1) Overall ticket sales 
 * 2) Reviewer's rating
 * 3) Default (both)
 */
@SuppressWarnings("resource")
public class ConfigureSystemControl implements MainControl {
    /**
	 * {@inheritDoc}
	 */
    @Override
    public void begin() {
        System.out.println("Select the criteria you want users to list the movies by:");
        System.out.println("1: Ticket Sales");
        System.out.println("2: Overall Ratings");
        System.out.println("3: Default (Ticket Sales and Overall Ratings)");

        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                System.out.print("Option: ");
                choice = sc.nextInt();
                if (choice >= 1 && choice <= 3)
                    break;
                else
                    System.out.println("Please enter a valid option");
            } catch (Exception e) {
                System.out.println("Please enter a valid option");
                sc.nextLine();
            }
        }

        DatabaseManager.getDataBase().setConfig(choice);

        NavigateControl.popOne();
    }
}
