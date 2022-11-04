package control;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.DatabaseManager;
import entity.Movie;
import boundary.ListView;
import boundary.MenuView;

/**
 * This class controls the display of the top 5 movies by overall ticket sales
 * or reviewer's rating
 */
public class TopMoviesControl implements MainControl {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void begin() {
		while (true) {
			int choice = MenuView.getMenuOption(
					"Top 5 movies by: ",
					"Total ticket sales",
					"Overall reviewer's rating",
					"Exit");

			List<String> movieStrings;

			switch (choice) {
				case 1:
					movieStrings = getTopMoviesByTicketSales();
					ListView.showStringList("Top 5 Movies By Ticket Sales", movieStrings, "No available data");
					break;
				case 2:
					movieStrings = getTopMoviesByOverallRating();
					ListView.showStringList("Top 5 Movies By Overall Rating", movieStrings, "No available data");
					break;
				case 3:
					NavigateControl.popOne();
					return;
			}
		}
	}

	/**
	 * This method determines the top 5 movies by ticket sales and stores it as a
	 * list of strings.
	 * 
	 * @return a {@code List} of {@code String} objects containing the information
	 *         about the top 5 movies by ticket sales
	 */
	private List<String> getTopMoviesByTicketSales() {
		List<Movie> movieList = DatabaseManager.getDataBase().getMovieList();

		List<Movie> movieList2 = new ArrayList<Movie>();
		movieList2.addAll(movieList);

		Comparator<Movie> salesComparator = Collections.reverseOrder(Comparator.comparing(Movie::getTotalSales));
		movieList2.sort(salesComparator);

		List<String> movieTitles = new ArrayList<String>();

		for (int i = 1; i <= 5 && i <= movieList2.size(); i++) {
			Movie movie = movieList2.get(i - 1);
			movieTitles.add(i + ". " + movie.getTitle() + " (Total Sales: $"
					+ String.format("%.2f", movie.getTotalSales()) + ")");
		}
		return movieTitles;
	}

	/**
	 * This method determines the top 5 movies by overall reviewer's rating and
	 * stores it in the form of a list of strings.
	 * 
	 * @return a {@code List} of {@code String} objects containing the information
	 *         about the top 5 movies by overall reviewer's rating
	 */
	private List<String> getTopMoviesByOverallRating() {
		List<Movie> movieList = DatabaseManager.getDataBase().getMovieList();
		List<Movie> moviesWithRatings = new ArrayList<Movie>();

		for (Movie movie : movieList) {
			if (movie.getAverageRating() != null) {
				moviesWithRatings.add(movie);
			}
		}

		Comparator<Movie> ratingComparator = Collections.reverseOrder(Comparator.comparing(Movie::getAverageRating));
		moviesWithRatings.sort(ratingComparator);

		List<String> movieTitles = new ArrayList<String>();

		for (int i = 1; i <= 5 && i < moviesWithRatings.size(); i++) {
			Movie movie = moviesWithRatings.get(i - 1);
			movieTitles.add(i + ". " + movie.getTitle() + " (Average Ratings: "
					+ String.format("%.2f", movie.getAverageRating()) + ")");
		}
		return movieTitles;
	}
}
