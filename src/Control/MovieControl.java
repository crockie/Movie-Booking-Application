//HAVENT CHANGED COMMENTS AND VARIABLES YET

package control;

import java.util.List;

import view.ListView;
import view.MenuView;
import view.MovieView;
import entity.Customer;
import entity.DatabaseManager;
import entity.Movie;

/**
 * This class controls the display of the movies and their details and reviews.
 */
public class MovieControl implements MainControl {
	/**
	 * The customer
	 */
	private Customer customer;
	/**
	 * The selected movie
	 */
	private Movie movie;
	
	/**
	 * Creates a new {@code MovieControl} object for the customer
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
				"View movie details",
				"View past reviews & ratings",
				"Add a review",
				"Exit"
			);
		
			switch (option) {
				case 1:
					MovieView.printMovieDetails(this.movie);
					break;
					
				case 2:
					ListView.displayLabelledItemList("Past Reviews & Ratings", movie.getReviewAndRating(), "No reviews & ratings available");
					break;
					
				case 3:
					MovieView.addMovieReview(this.movie, this.customer);
					break;
					
				case 4:
					NavigationController.goBack();
					return;
			}
		}
	}
	
	/**
	 * This method controls the selection of the movie
	 * @return the selected movie
	 */
	private Movie selectMovie() {
		List<Movie> movieList = DatabaseManager.getDataBase().getMovieList();
		return MenuView.getLabelledItem("Select a Movie", movieList);
	}
}
