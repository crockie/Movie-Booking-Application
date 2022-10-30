package Entity;

public enum AgeGroup implements ItemName{
	SENIOR_CITIZEN("Senior Citizen"), ADULT("Adult"), CHILD("Child");
	private String name;
	
	private AgeGroup(String name) {
		this.name = name;
	}
	
	@Override
	public String nameToString() {
		return name;
	}
}
