package control;

import java.util.Scanner;

import entity.DatabaseManager;

public class ConfigureSystemControl implements MainControl {
    @Override
    public void begin() {
        System.out.println("Select the criteria you want users to list the movies by:");
        System.out.println("0: Ticket Sales");
        System.out.println("1: Overall Ratings");
        System.out.print("Option: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        DatabaseManager.getDataBase().setConfig(choice);

        NavigateControl.popOne();
    }
}
