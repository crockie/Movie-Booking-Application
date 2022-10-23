package Entity;

// Show statuses for a movie
public enum ShowStatus implements LabelledItem {

    COMING_SOON("Coming Soon"),

    PREVIEW("Preview"),

    NOW_SHOWING("Now Showing"),

    END_OF_SHOWING("End of Showing"); /** Assignment does not ask for End Of Showing, delete? */

    String label;

    private ShowStatus(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}