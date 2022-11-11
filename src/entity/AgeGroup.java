package entity;

/**
 * The different age groups available for ticket pricing
 */
public enum AgeGroup implements ItemName{
	/**
	 * Senior citizen
	 */
	SENIOR_CITIZEN("Senior Citizen"),
	/**
	 * Adult
	 */
	ADULT("Adult"),
	/**
	 * Child
	 */
	CHILD("Child");
	/**
	 * The name of the age group
	 */
	private String ageGroup;
	
	/**
	 * This creates a {@code AgeGroup} object with the input
	 * @param ageGroup
	 */
	private AgeGroup (String ageGroup) {
		this.ageGroup = ageGroup;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String nameToString() {
		return ageGroup;
	}
}
