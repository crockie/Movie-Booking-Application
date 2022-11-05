package entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class contains all the information of a cinema
 */
public class Cinema implements Serializable, ItemName{
    /**
     * Cinema code of the cinema
     */
    private String cinemaCode;

    /**
     * The cinema class of the cinema
     */
    private CinemaClass cinemaClass;
    /**
     * The layout of the cinema
     */
    private boolean[][] seatLayout;
    /**
     * The list of showtimes in the cinema
     */
    private ArrayList<MovieTime> movieTimes = new ArrayList<MovieTime>();
    /**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = 48485464514484848L; //change serialisable
	
    /**
	 * Creates a {@code Cinema} object for the given cinema name, cinema class and layout
	 * @param cinemaCode the code of the cinema
	 * @param cinemaClass the cinema class of the cinema
     * @param seatLayout the seating layout of the cinema
	 */
    public Cinema(String cinemaCode, CinemaClass cinemaClass, boolean[][] seatLayout){
        this.cinemaCode = cinemaCode;
        this.cinemaClass = cinemaClass;
        this.seatLayout = seatLayout;
    }

    
    /** 
     * Create {@code MovieTime} object and add it to the list of movieTimes
     * @param movieDateTime the date and time of the movie
     * @param movie the movie to be shown
     */
    public void addMovieTime(LocalDateTime movieDateTime, Movie movie){
        MovieTime movieTime = new MovieTime(this, movieDateTime, movie);
        this.movieTimes.add(movieTime);
    }

    
    /** 
     * @return String the name of the cinema
     */
    public String getCinemaCode(){
        return this.cinemaCode;
    }
    
    /** 
     * @return CinemaClass the cinema class of the cinema
     */
    public CinemaClass getCinemaClass(){
        return this.cinemaClass;
    }
    
    /** 
     * @return boolean[][] the seating layout of the cinema
     */
    public boolean[][] getSeatLayout(){
        return this.seatLayout;
    }
    
    /**  
     * @return ArrayList<MovieTime> the showtimes in the cinema
     */
    public ArrayList<MovieTime> getMovieTimes(){
        return this.movieTimes;
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public String nameToString() {
        return this.cinemaCode;
    }
}
