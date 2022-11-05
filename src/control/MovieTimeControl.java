package control;

import boundary.ShowtimeView;

/**
 * This class controls the display of all the available movie times
 */
public class MovieTimeControl implements MainControl {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void begin() {
		ShowtimeView.getShowtimeView();
		NavigateControl.popOne();
	}
}
