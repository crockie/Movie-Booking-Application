package entity;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * This class contains all the information of a cineplex
 */
public class Cineplex implements Serializable, ItemName{
    /**
	 * Name of the cineplex
	 */
    private String name;
    /**
	 * List of cinemas in the cineplex
	 */
    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();

    /**
	 * Serialisation version number
	 */
    private static final long serialVersionUID = 2510448747911179290L; //change serialisable

    /**
	 * Creates a {@code Cineplex} with the given name
	 * @param name the name of the cineplex
	 */
    public Cineplex(String name){
        this.name = name;
    }
    
    /** 
     * Creates cinema and add it to the list of cinemas
     * @param cinemaName the name of the cinema
     * @param cinemaClass the cinema class of the cinema
     * @param seatLayout the seating layout of the cinema
     */
    public void addCinema(String cinemaName, CinemaClass cinemaClass, boolean[][] seatLayout){
        Cinema cinema = new Cinema(cinemaName, cinemaClass, seatLayout);
        this.cinemas.add(cinema);
    }

    
    /** 
     * Return the list of movieTimes in all the cinemas under the cineplex
     * @return ArrayList<MovieTime> the list of movieTimes 
     */
    public ArrayList<MovieTime> getMovieTimes(){
        ArrayList<MovieTime> movieTimes = new ArrayList<MovieTime>();

        for(Cinema cinema: cinemas){
            movieTimes.addAll(cinema.getMovieTimes());
        }
        return movieTimes;
    }
    
    
    /** 
     * Return the list of cinemas under the cineplex
     * @return ArrayList<Cinema> the list of cinemas
     */
    public ArrayList<Cinema> getCinemas(){
        return cinemas;
    }

    
    /** 
     * Return the name of the cineplex
     * @return String the name of the cineplex
     */
    public String getName(){
        return name;
    }

    
    /** 
     * {@inheritDoc}
     */
    @Override
    public String nameToString(){
        return name;
    }
}
