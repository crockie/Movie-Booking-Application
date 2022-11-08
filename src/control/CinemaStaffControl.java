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
                "Create/Update/Remove Movie Listing",
                "Create/Update/Remove Cinema Showtimes",
                "Create/Update/Remove Ticket Pricing Scheme & Holiday",
                "List Top 5 Movies",
                "View Ticket Pricing Scheme",
                "View All Movies",
                "View Cinema Showtimes",
                "View Holidays",
                "Configure Top 5 View",

                "Exit");

        switch (choice) {
            case 1:
                NavigateControl.load(new MovieLogicControl());
                break;

            case 2:
                NavigateControl.load(new MovieTimeLogicControl());
                break;

            case 3:
                NavigateControl.load(new TicketPriceLogicControl());
                break;

            case 4:
                NavigateControl.load(new TopMoviesControl(false));
                break;

            case 5:
                NavigateControl.load(new TicketPriceControl());
                break;

            case 6:
                NavigateControl.load(new DisplayMovieControl(false));
                break;

            case 7:
                NavigateControl.load(new MovieTimeControl());
                break;

            case 8:
                NavigateControl.load(new HolidayControl());
                break;

            case 9:
                NavigateControl.load(new ConfigureSystemControl());
                break;

            case 10:
                NavigateControl.popOne();
                break;
        }
    }
}