package Entity;

/**
* The cinema classes for a cinema
*/
public enum CinemaClass {
    /**
    * Standard cinema class
    */
    STANDARD("Standard"),
 
    /**
    * Platinum Movie Suite cinema class
    */
    PLAT_MOVIE_SUITES("Platinum Movie Suite");

    /**
    * The name of the cinema class
    */
    private String cinemaClass;

    /**
    * Creates a {@code CinemaClass} object with the given cinema class
    * @param cinemaClass
    */
    private CinemaClass(String cinemaClass){
        this.cinemaClass = cinemaClass;
    }

    /**
    * !!!
    * Should we stick with implementing ItemName instead of this?
    */
    private String getCinemaClass(){
        return this.cinemaClass;
    }   
}
