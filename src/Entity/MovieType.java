package Entity;

// Type of movie
public enum MovieType implements LabelledItem {

    _3D("3D"),

    BLOCKBUSTER("Blockbuster"),

    REGULAR("Regular");

    private final String label;

    private MovieType(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
