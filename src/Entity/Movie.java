package Entity;

import java.time.Duration;
import java.util.ArrayList;
import java.io.Serializable;

public class Movie implements ItemName, Serializable {
	/*
	 * Serialization number
	 */
	private static final long serialVersionUID = 5822630624504041207L;
	/*
	 * Title of Movie
	 */
	private String title;
	/*
	 * Synopsis of Movie
	 */
	private String synopsis;
	/*
	 * Director of Movie
	 */
	private String director;
	/*
	 * Casts of Movie
	 */
	private String[] cast;
	/*
	 * Different Status for Movies: Coming Soon, Preview, Now Showing (End Of Showing)
	 */ 
	private ShowStatus showStatus;
	/*
	 * Different Movie Ratings: G, PG, PG13, NC16, M18, R21
	 */
	private MovieRating movieRating;
	/*
	 * Different Movie Types: Regular, Blockbuster, 3D
	 */
	private MovieType movieType;
	/*
	 * ArrayList of ReviewAndRating
	 */
	private ArrayList<ReviewAndRating> reviewAndRating = new ArrayList<ReviewAndRating>();
	/*
	 * ArrayList of MovieTime
	 */
	private ArrayList<MovieTime> movieTimes = new ArrayList<MovieTime>();
	
	/*
	 * Movie screening duration
	 */
	private Duration duration;
	/*
	 * Constructor for Movie
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
	/*
	 * Add new movieTime into ArrayList movieTimes
	 */
	protected void addMovieTime(MovieTime movieTime) {
		this.movieTimes.add(movieTime);
	}
	/*
	 * Getter for MovieTime
	 */
	public ArrayList<MovieTime> getMovieTimes() {
		return movieTimes;
	}
	/*
	 * Calculates the average rating of the Movie
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
	/*
	 * Calculates the Total Sales of the Movie
	 */
	public double getTotalSales() {
		double totalSales = 0;
		
		for (MovieTime movieTime: movieTimes)
			totalSales += movieTime.getTotalSales();
		
		return totalSales;
	}
	/*
	 * 
	 */
	@Override
	public String getLabel() {
		return title;
	}
	/*
	 * Getter for Movie title
	 */
	public String getTitle() {
		return title;
	}
	/*
	 * Getter for Movie synopsis 
	 */
	public String getSynopsis() {
		return synopsis;
	}
	/*
	 * Getter for Movie director
	 */
	public String getDirector() {
		return director;
	}
	/*
	 * Getter for Movie cast
	 */
	public String[] getCast() {
		return cast;
	}
	/*
	 * Getter for MovieRating
	 */
	public MovieRating getMovieRating() {
		return movieRating;
	}
	/*
	 * Getter for MovieType
	 */
	public MovieType getMovieType() {
		return movieType;
	}
	/*
	 * Getter for ArrayList ReviewAndRating
	 */
	public ArrayList<ReviewAndRating> getReviewAndRating() {
		return reviewAndRating;
	}
	/*
	 * Getter for Movie showStatus
	 */
	public ShowStatus getShowStatus() {
		return showStatus;
	}
	/*
	 * Getter for Movie duration
	 */
	public Duration getDuration() {
		return duration;
	}
	/*
	 * Setter for Movie title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/*
	 * Setter for Movie synopsis
	 */
	public void setSynopsis(String synopsis) {  
		this.synopsis = synopsis;
	}
	/*
	 * Setter for Movie director
	 */
	public void setDirector(String director) {  
		this.director = director;
		
	}
	/*
	 * Setter for Movie cast
	 */
	public void setCast(String[] cast) {
		this.cast = cast;
	}
	/*
	 * Setter for movieRating
	 */
	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;	
	}
	/*
	 * Setter for movieType
	 */
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}
	/*
	 * Setter for Movie duration
	 */
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	/*
	 * Setter for Movie showStatus
	 */
	public void setShowStatus(ShowStatus showStatus) {
		this.showStatus = showStatus;
	}
}
