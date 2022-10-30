package control;

import java.util.List;

import entity.DatabaseManager;
import entity.Movie;
import view.MenuView;
import view.MovieEditView;
import view.MovieView;

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
				"View movie details",
				"Add movie",
				"Update movie",
				"Remove movie",
				"Exit"
			);
			
			switch (option) {
				case 1: 
					Movie movie = MenuView.getLabelledItem("Choose a movie", movieList);
					MovieView.printMovieDetails(movie);
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
}
