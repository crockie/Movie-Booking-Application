package Entity;

/**
 * The different date categories available for ticket pricing
 */
public enum DateGroup implements ItemName{
	WEEKDAY("Weekday"), WEEKEND("Weekend"), HOLIDAY("Holiday");
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
