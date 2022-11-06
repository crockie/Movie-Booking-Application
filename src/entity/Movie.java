package entity;

import java.time.Duration;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class contains the information for the Movie
 */
public class Movie implements ItemName, Serializable {
	/**
	 * Serialization number
	 */
	private static final long serialVersionUID = 544548948489484151L;
	/**
	 * Title of Movie
	 */
	private String title;
	/**
	 * Synopsis of Movie
	 */
	private String synopsis;
	/**
	 * Director of Movie
	 */
	private String director;
	/**
	 * Casts of Movie
	 */
	private String[] cast;
	/**
	 * Different Status for Movies: Coming Soon, Preview, Now Showing (End Of Showing)
	 */ 
	private ShowStatus showStatus;
	/**
	 * Different Movie Ratings: G, PG, PG13, NC16, M18, R21
	 */
	private MovieRating movieRating;
	/**
	 * Different Movie Types: Regular, Blockbuster, 3D
	 */
	private MovieType movieType;
	/**
	 * ArrayList of ReviewAndRating
	 */
	private ArrayList<ReviewAndRating> reviewAndRating = new ArrayList<ReviewAndRating>();
	/**
	 * ArrayList of MovieTime
	 */
	private ArrayList<MovieTime> movieTimes = new ArrayList<MovieTime>();
	
	/**
	 * Movie screening duration
	 */
	private Duration duration;
	/**
	 * Constructor for Movie
	 * Creates a {@code Movie} object with the given title, synopsis, director, cast, show status, movie rating, movie type
	 * @param title the title of the Movie
	 * @param synopsis the synopsis of the Movie
	 * @param director the director of the Movie
	 * @param cast the casts of the Movie
	 * @param showStatus the show status of the Movie
	 * @param movieRating the rating of the Movie
	 * @param movieType the type of Movie
	 * @param duration the time that the Movie runs for
	 */
	public Movie(String title, String synopsis, String director, String[] cast, ShowStatus showStatus, MovieRating movieRating,
			MovieType movieType, Duration duration) 
	{
		this.title = title;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.showStatus = showStatus;
		this.movieRating = movieRating;
		this.movieType = movieType;
		this.duration = duration;
	}
	/**
	 * Add new movieTime into ArrayList movieTimes
	 * @param movieTime the new Movie Time to be added
	 */
	protected void addMovieTime(MovieTime movieTime) {
		this.movieTimes.add(movieTime);
	}
	/**
	 * Getter for MovieTime
	 * @return the list of times for the Movie
	 */
	public ArrayList<MovieTime> getMovieTimes() {
		return movieTimes;
	}
	/**
	 * Calculates the average rating of the Movie
	 * @return the average rating of the Movie
	 */
	public Double getAverageRating() {
		double sum = 0.0;
		double totalRating;
		int reviewCount = reviewAndRating.size();
		
		if (reviewCount <= 1)
			return null;
		
		for (ReviewAndRating reviewAndRating: reviewAndRating)
			sum += reviewAndRating.getRating();
		
		totalRating = sum / reviewCount;
		return totalRating;
	}
	/**
	 * Calculates the Total Sales of the Movie
	 * @return the total sales of the Movie
	 */
	public double getTotalSales() {
		double totalSales = 0;
		
		for (MovieTime movieTime: movieTimes)
			totalSales += movieTime.getTotalSales();
		
		return totalSales;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String nameToString() {
		return title;
	}
	/**
	 * Getter for Movie title
	 * @return title of the Movie
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Getter for Movie synopsis 
	 * @return synopsis of the Movie
	 */
	public String getSynopsis() {
		return synopsis;
	}
	/**
	 * Getter for Movie director
	 * @return director of the Movie
	 */
	public String getDirector() {
		return director;
	}
	/**
	 * Getter for Movie cast
	 * @return casts of the Movie
	 */
	public String[] getCast() {
		return cast;
	}
	/**
	 * Getter for MovieRating
	 * @return rating of the Movie
	 */
	public MovieRating getMovieRating() {
		return movieRating;
	}
	/**
	 * Getter for MovieType
	 * @return type of Movie
	 */
	public MovieType getMovieType() {
		return movieType;
	}
	/**
	 * Getter for ArrayList ReviewAndRating
	 * @return reviews and ratings of the Movie
	 */
	public ArrayList<ReviewAndRating> getReviewAndRating() {
		return reviewAndRating;
	}
	/**
	 * Getter for Movie showStatus
	 * @return show status of the Movie
	 */
	public ShowStatus getShowStatus() {
		return showStatus;
	}
	/**
	 * Getter for Movie duration
	 * @return duration of the Movie
	 */
	public Duration getDuration() {
		return duration;
	}
	/**
	 * Setter for Movie title
	 * @param title new title of the Movie
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Setter for Movie synopsis
	 * @param synopsis new synopsis for the Movie
	 */
	public void setSynopsis(String synopsis) {  
		this.synopsis = synopsis;
	}
	/**
	 * Setter for Movie director
	 * @param director new director of the Movie
	 */
	public void setDirector(String director) {  
		this.director = director;
		
	}
	/**
	 * Setter for Movie cast
	 * @param cast new casts for the Movie
	 */
	public void setCast(String[] cast) {
		this.cast = cast;
	}
	/**
	 * Setter for movieRating
	 * @param movieRating new Movie Rating for the Movie
	 */
	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;	
	}
	/**
	 * Setter for movieType
	 * @param movieType new type for the Movie
	 */
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}
	/**
	 * Setter for Movie duration
	 * @param duration new duration for the Movie
	 */
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	/**
	 * Setter for Movie showStatus
	 * @param showStatus new show status for the Movie
	 */
	public void setShowStatus(ShowStatus showStatus) {
		this.showStatus = showStatus;
	}
}
