package control;

import boundary.ShowTimeView;

/**
 * This class controls the display of all the available movie times
 */
public class MovieTimeControl implements MainControl {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void begin() {
		ShowTimeView.displayAllShowTimes();
		NavigateControl.popOne();
	}
}
