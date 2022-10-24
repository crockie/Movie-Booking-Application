package Entity;

// Show statuses for a movie
public enum ShowStatus implements LabelledItem {

    COMING_SOON("Coming Soon"),

    PREVIEW("Preview"),

    NOW_SHOWING("Now Showing"),

    END_OF_SHOWING("End of Showing"); /** Assignment does not ask for End Of Showing, delete? */

    String name;

    private ShowStatus(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
