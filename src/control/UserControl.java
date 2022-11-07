package control;

import javax.xml.crypto.Data;

import boundary.MenuView;
import entity.DatabaseManager;

/**
 * Controls the selection of the respective user portal
 */
public class UserControl implements MainControl {

    /**
     * {@inheritDoc}
     */
    @Override
    public void begin() {
        System.out.println("Welcome to the MOvie Booking and LIsting Management Application (MOBLIMA)!");
        int choice = MenuView.getMenuOption(
                "Please select a portal:",
                "Customer",
                "Cinema Staff",
                "Exit");

        switch (choice) {
            case 1:
                NavigateControl.load(new CustomerControl());
                break;

            case 2:
                NavigateControl.load(new CinemaStaffControl());
                break;

            case 3:
                System.out
                        .println("Thank you for using the MOvie Booking and LIsting Management Application (MOBLIMA)!");
                NavigateControl.popOne();
                break;
        }
    }

    public static void main(String[] args) {
        DatabaseManager.read();

        UserControl controller = new UserControl();
        controller.begin();

        DatabaseManager.write();
    }
}
