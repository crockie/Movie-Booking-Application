package Entity;

/**
 * The movie ratings 
 */
public enum MovieRating implements ItemName {

   /**
   * General
   */
    G("G"),

    /**
    * Parental Guidance
    */
    PG("PG"),

    /**
    * Parental Guidance 13
    */
    PG13("PG13"),

    /**
    * No Children Under 16
    */
    NC16("NC16"),

    /**
    * Mature 18
    */
    M18("M18"),
	
    /**
    * Restricted 21
    */
    R21("R21");

    /**
    * The name of the release rating
    */
    private final String name;

    /**
    * Creates a {@code MovieRating} object with the given name
    * @param name
    */
    private MovieRating(String name) {
        this.name = name;
    }
	
    /**
    * {@inheritDoc}
    */
    @Override
    public String nameToString() {
        return name;
    }
}
