package control;

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
        System.out.println("Welcome to the MOvie Booking and LIsting Management Application (MOBLIMA)!");
        int choice = MenuView.getMenuOption(
                "Log in as:",
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
}
