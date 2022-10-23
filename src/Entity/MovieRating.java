package Entity;


// Movie rating
public enum MovieRating implements LabelledItem {

    G("G"),

    PG("PG"),

    PG13("PG13"),

    NC16("NC16"),

    M18("M18"),

    R21("R21");

    private final String label;

    private MovieRating(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}