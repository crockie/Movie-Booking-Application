package entity;

/**
 * This class show the movie statuses
 */
public enum ShowStatus implements ItemName {
    /**
     * Coming soon
     */
    COMING_SOON("Coming Soon"),

    /**
     * Preview
     */    
    PREVIEW("Preview"),

    /**
     * Now showing
     */    
    NOW_SHOWING("Now Showing"),

    /**
     * End of showing
     */    
    END_OF_SHOWING("End of Showing");

    /**
     * The name of the showing status
     */  
    private String name;

    /**
     * This constructor creates a {@code ShowStatus} object with the given label
     * @param name the show status
     */ 
    private ShowStatus(String name) {
        this.name = name;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String nameToString() {
        return this.name;
    }
}
