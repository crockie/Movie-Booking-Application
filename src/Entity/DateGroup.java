package entity;

/**
 * The different date categories available for ticket pricing
 */
public enum DateGroup implements ItemName{
	/**
	 * Weekday
	 */
	WEEKDAY("Weekday"), 
	/**
	 * Weekend
	 */
	WEEKEND("Weekend"),
	/**
	 * Holiday
	 */
	HOLIDAY("Holiday");
	private String dateGroup;
	
	/**
	 * This creates a {@code DateGroup} object with the input
	 * @param dateGroup
	 */
	private DateGroup(String dateGroup) {
		this.dateGroup = dateGroup;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String nameToString() {
		return dateGroup;
	}
}
