package entity;

/**
* The cinema classes for a cinema
*/
public enum CinemaClass implements ItemName{
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
	 * {@inheritDoc}
	 */
	@Override
    public String nameToString(){
        return this.cinemaClass;
    }   
}
