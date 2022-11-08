package control;

import java.util.List;

import boundary.MovieEditView;
import boundary.MovieView;
import boundary.MenuView;
import entity.DatabaseManager;
import entity.Movie;



/**
 * This class controls the creation, change and removal of movies
 */
public class MovieLogicControl implements MainControl {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void begin() {
		List<Movie> movieList = DatabaseManager.getDataBase().getMovieList();

		while (true) {
			int option = MenuView.getMenuOption(
					"Enter your choice: ",
					"Display movie details",
					"Add movie",
					"Update movie",
					"Remove movie",
					"Exit");

			switch (option) {
				case 1:
					Movie movie = MenuView.getItemName("Choose a movie", movieList);
					MovieView.getMovieView(movie);
					break;

				case 2:
					MovieEditView.addMovie();
					break;

				case 3:
					MovieEditView.updateMovie();
					break;

				case 4:
					MovieEditView.removeMovie();
					break;

				case 5:
					NavigateControl.popOne();
					return;
			}
		}
		
	}
	public static void main(String[] args) {
		DatabaseManager.read();
		
		MovieLogicControl controller = new MovieLogicControl();
		controller.begin();
		
		DatabaseManager.write();
	}
}
