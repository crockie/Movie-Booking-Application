package control;

import java.util.ArrayList;
import java.util.List;

import boundary.ListView;
import boundary.MenuView;
import boundary.MovieView;
import entity.Customer;
import entity.DatabaseManager;
import entity.Movie;
import entity.ShowStatus;

/**
 * This class controls the display of the movies and their details and reviews.
 */
public class MovieControl implements MainControl {
	/**
	 * Customer
	 */
	private Customer customer;
	/**
	 * The selected movie
	 */
	private Movie movie;

	/**
	 * Creates a new {@code MovieControl} object for the customer
	 * 
	 * @param customer the customer that wants to view the movie
	 */
	public MovieControl(Customer customer) {
		this.customer = customer;
	}

	/**
	 * {@inheritDoc}
	 */
	public void begin() {
		this.movie = selectMovie();

		while (true) {
			int option = MenuView.getMenuOption(
					this.movie.getTitle(),
					"Display movie details",
					"View past reviews & ratings",
					"Input a review",
					"Exit");

			switch (option) {
				case 1:
					MovieView.getMovieView(this.movie);
					break;

				case 2:
					ListView.showItemList("Reviews & Ratings", movie.getReviewAndRating(),
							"No Reviews & Ratings available");
					break;

				case 3:
					MovieView.addMovieReview(this.movie, this.customer);
					break;

				case 4:
					NavigateControl.popOne();
					return;
			}
		}
	}

	/**
	 * This method controls the selection of the movie
	 * 
	 * @return the selected movie
	 */
	private Movie selectMovie() {
		List<Movie> movieList = DatabaseManager.getDataBase().getMovieList();
		List<Movie> availableMovieList = new ArrayList<Movie>();

		for (Movie movie : movieList) {
			if (movie.getShowStatus() != ShowStatus.END_OF_SHOWING)
				availableMovieList.add(movie);
		}
		return MenuView.getItemName("Select a Movie", availableMovieList);
	}
}
