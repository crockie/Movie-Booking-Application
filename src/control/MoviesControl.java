package control;

import boundary.MovieView;

public class MoviesControl implements MainControl {
    private boolean isCustomer;

    public MoviesControl(boolean isCustomer) {
        this.isCustomer = isCustomer;
    }

    @Override
    public void begin() {
        MovieView.getAllMoviesView(isCustomer);
        NavigateControl.popOne();
    }
}
