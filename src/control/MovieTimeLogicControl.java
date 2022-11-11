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
				"Display Movie times",
				"Add a Movie Time",
				"Update a Movie Time",
				"Remove a Movie Time",
				"Exit"
			);
			
			switch (option) {
				case 1:
					ShowTimeView.getShowTimeView();
					break;
					
				case 2:
					ShowTimeEditView.addMovieTime();
					break;
					
				case 3:
					ShowTimeEditView.updateMovieTime();
					break;
					
				case 4:
					ShowTimeEditView.removeMovieTime();
					break;
					
				case 5:
					NavigateControl.popOne();
					return;
			}
		}
	}
}
