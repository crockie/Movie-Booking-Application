package Entity;

/**
 * The different age groups available for ticket
 */
public enum AgeGroup implements ItemName{
	SENIOR_CITIZEN("Senior Citizen"), ADULT("Adult"), CHILD("Child");
	private String ageGroup;
	
	/**
	 * This creates a {@code AgeGroup} object with the input
	 * @param ageGroup
	 */
	private AgeGroup(String ageGroup) {
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
