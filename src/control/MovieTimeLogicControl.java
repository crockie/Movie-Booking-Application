package control;

import boundary.*;

/**
 * This class controls the creation, change and removal of showtimes
 */
public class MovieTimeLogicControl implements MainControl {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void begin() {
		while (true) {
			int option = MenuView.getMenuOption(
				"Enter your choice: ",
				"View movie times",
				"Add a Movie Time",
				"Update a Movie Time",
				"Remove a Movie Time",
				"Exit"
			);
			
			switch (option) {
				case 1:
					ShowtimeView.getShowtimeView();
					break;
					
				case 2:
					MovieTimeEditView.addMovieTime();
					break;
					
				case 3:
					MovieTimeEditView.updateMovieTime();
					break;
					
				case 4:
					MovieTimeEditView.removeMovieTime();
					break;
					
				case 5:
					NavigateControl.popOne();
					return;
			}
		}
	}
}
