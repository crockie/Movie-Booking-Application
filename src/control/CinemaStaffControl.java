package control;

import entity.CinemaStaff;
import boundary.CinemaStaffLoginView; 
import boundary.MenuView;

/**
 * Controls the main menu and login for the cinema staff
 */
public class CinemaStaffControl implements MainControl {
    /**
     * Cinema staff that is currently logged in
     */
    private CinemaStaff cinemaStaff;

    /**
     * {@inheritDoc}
     */
    @Override
    public void begin() {
        while (this.cinemaStaff == null) {
            int choice = MenuView.getMenuOption(
                    "Please select an option:",
                    "Login",
                    "Exit");

            switch (choice) {
                case 1:
                    this.cinemaStaff = CinemaStaffLoginView.loginCinemaStaff();
                    break;

                case 2:
                    NavigateControl.popOne();
                    return;
            }
        }

        showMenu();
    }

    /**
     * Controls main menu display for the cinema staff
     */
    private void showMenu() {
        int choice = MenuView.getMenuOption(
                "Please select an option:",
                "List Top 5 Movies",
                "Modify Ticket Pricing Scheme",
                "Modify Movie Listings",
                "Modify Cinema Showtimes",

                "Exit");

        switch (choice) {
            case 1:
                NavigateControl.load(new TopMoviesControl());
                break;

            case 2:
                NavigateControl.load(new TicketPriceLogicControl());
                break;

            case 3:
                NavigateControl.load(new MovieLogicControl());
                break;

            case 4:
                NavigateControl.load(new MovieTimeLogicControl());
                break;

            case 5:
                NavigateControl.popOne();
                break;
        }
    }
}