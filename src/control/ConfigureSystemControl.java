package control;

import java.util.Scanner;

import entity.DatabaseManager;

public class ConfigureSystemControl implements MainControl {
    @Override
    public void begin() {
        System.out.println("Select the criteria you want users to list the movies by:");
        System.out.println("1: Ticket Sales");
        System.out.println("2: Overall Ratings");
        System.out.println("3: Default (Ticket Sales and Overall Ratings)");
        System.out.print("Option: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if(choice < 1 || choice > 3){
            System.out.println("Invalid input. Please try again.");
            return;
        }

        DatabaseManager.getDataBase().setConfig(choice);

        NavigateControl.popOne();
    }
}
