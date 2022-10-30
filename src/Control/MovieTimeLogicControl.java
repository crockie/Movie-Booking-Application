package control;

import view.MenuView;
import view.ShowTimeEditView;
import view.ShowTimeView;

/**
 * This class controls the creation, modification and removal of showtimes
 */
public class MovieTimeEditController implements MainControl {

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
					ShowTimeView.displayAllShowTimes();
					break;
					
				case 2:
					ShowTimeEditView.addShowTime();
					break;
					
				case 3:
					ShowTimeEditView.updateShowTime();	
					break;
					
				case 4:
					ShowTimeEditView.removeShowTime();
					break;
					
				case 5:
					NavigationController.goBack();
					return;
			}
		}
	}
}
