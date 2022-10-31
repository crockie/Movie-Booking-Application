package control;

import boundary.IOController;
import boundary.MenuView;

/**
 * Controls the selection of the respective user portal
 */
public class UserControl implements MainControl {

    /**
     * {@inheritDoc}
     */
    @Override
    public void begin() {
        IOController.displayMessage("Welcome to the MOvie Booking and LIsting Management Application (MOBLIMA)!");
        int choice = MenuView.getMenuOption(
                "Please select a portal:",
                "Customer",
                "Cinema Staff",
                "Exit"
        );

        switch (choice) {
            case 1:
                NavigateControl.load(new MovieGoerController()); // Name it CustomerControl (?)
                break;

            case 2:
                NavigateControl.load(new CinemaStaffController());
                break;

            case 3:
                IOController.displayMessage("Thank you for using the MOvie Booking and LIsting Management Application (MOBLIMA)!");
                NavigateControl.popOne();
                break;
        }
    }
}