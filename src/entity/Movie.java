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
	 * This constructor creates a {@code Movie} object with the given title, synopsis, director, cast, show status, movie rating, movie type
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
	 * This method adds a new movieTime into ArrayList movieTimes
	 * @param movieTime the new Movie Time to be added
	 */
	protected void addMovieTime(MovieTime movieTime) {
		this.movieTimes.add(movieTime);
	}
	
	/**
	 * This method gets the lists of times for the Movie
	 * @return the list of times for the Movie
	 */
	public ArrayList<MovieTime> getMovieTimes() {
		return movieTimes;
	}
	
	/**
	 * This method calculates the average rating of the Movie
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
	 * This method calculates the Total Sales of the Movie
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
	 * This method gets the Movie title of the Movie
	 * @return title of the Movie
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * This methid gets the Movie synopsis of the Movie
	 * @return synopsis of the Movie
	 */
	public String getSynopsis() {
		return synopsis;
	}
	
	/**
	 * This methid gets the Movie director of the Movie
	 * @return director of the Movie
	 */
	public String getDirector() {
		return director;
	}
	
	/**
	 * This methid gets the Movie cast of the movie
	 * @return casts of the Movie
	 */
	public String[] getCast() {
		return cast;
	}
	
	/**
	 * This method gets the Movie Rating of the movie
	 * @return rating of the Movie
	 */
	public MovieRating getMovieRating() {
		return movieRating;
	}
	
	/**
	 * This method gets the for Movie Type
	 * @return type of Movie
	 */
	public MovieType getMovieType() {
		return movieType;
	}
	
	/**
	 * This method gets the ArrayList of ReviewAndRating of the Movie
	 * @return reviews and ratings of the Movie
	 */
	public ArrayList<ReviewAndRating> getReviewAndRating() {
		return reviewAndRating;
	}
	
	/**
	 * This metod gets the Movie showStatus
	 * @return show status of the Movie
	 */
	public ShowStatus getShowStatus() {
		return showStatus;
	}
	
	/**
	 * This method gets the Movie duration
	 * @return duration of the Movie
	 */
	public Duration getDuration() {
		return duration;
	}
	
	/**
	 * This method sets the Movie title
	 * @param title new title of the Movie
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * This method sets the Movie synopsis
	 * @param synopsis new synopsis for the Movie
	 */
	public void setSynopsis(String synopsis) {  
		this.synopsis = synopsis;
	}
	
	/**
	 * This method sets the Movie director of the Movie
	 * @param director new director of the Movie
	 */
	public void setDirector(String director) {  
		this.director = director;
		
	}
	
	/**
	 * This method sets the Movie cast of the Movie
	 * @param cast new casts for the Movie
	 */
	public void setCast(String[] cast) {
		this.cast = cast;
	}
	
	/**
	 * This method sets the movieRating for the Movie
	 * @param movieRating new Movie Rating for the Movie
	 */
	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;	
	}
	
	/**
	 * This method sets the movieType for the Movie
	 * @param movieType new type for the Movie
	 */
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}
	
	/**
	 * This method sets the Movie duration of the Movie
	 * @param duration new duration for the Movie
	 */
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	/**
	 * This method sets the Movie's showStatus
	 * @param showStatus new show status for the Movie
	 */
	public void setShowStatus(ShowStatus showStatus) {
		this.showStatus = showStatus;
	}
}
