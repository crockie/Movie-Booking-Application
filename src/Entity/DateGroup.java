package Entity;

public enum DateGroup implements ItemName{
	WEEKDAY("Weekday"), WEEKEND("Weekend"), HOLIDAY("Holiday");
	private String name;
	
	private DateGroup(String name) {
		this.name = name;
	}
	
	@Override
	public String nameToString() {
		return name;
	}
}
