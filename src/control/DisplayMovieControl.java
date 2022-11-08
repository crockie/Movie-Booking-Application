package control;

import boundary.MovieView;

public class DisplayMovieControl implements MainControl {
    private boolean isCustomer;

    public DisplayMovieControl(boolean isCustomer) {
        this.isCustomer = isCustomer;
    }

    @Override
    public void begin() {
        MovieView.getAllMoviesView(isCustomer);
        NavigateControl.popOne();
    }
}
