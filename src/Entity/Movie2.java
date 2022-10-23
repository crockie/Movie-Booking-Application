package model;

import java.time.Duration;
import java.util.ArrayList;

public class Movie2 implements ItemLabel {
	private String title;
	
	private String synopsis;
	
	private String director;
	
	private String[] cast;
	
	// Coming Soon, Preview, Now Showing, End Of Showing 
	private ShowStatus showStatus;
	
	// PG, NC16, M18
	private MovieRating movieRating;
	
	// Blockbuster, 3D
	private MovieType movieType;
	
	private ArrayList<ReviewAndRating> reviewAndRating = new ArrayList<ReviewAndRating>();
	
	private ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
	
	// might change
	private Duration duration;
	
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
	
	protected void addShowTime(ShowTime showTime) {
		this.showTimes.add(showTime);
	}
	
	public ArrayList<ShowTime> getShowTimes() {
		return showTimes;
	}
	
	public Double getTotalRating() {
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
	
	public double getTotalSales() {
		double totalSales = 0;
		
		for (MovieTime movieTime: movieTimes)
			totalSales += movieTime.getTotalSales();
		
		return totalSales;
	}

	@Override
	public String getLabel() {
		return title;
	}
	
	public String getTitle() {
		return title;
	}

	public String getSynopsis() {
		return synopsis;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String[] getCast() {
		return cast;
	}

	public MovieRating getMovieRating() {
		return movieRating;
	}

	public MovieType getMovieType() {
		return movieType;
	}

	public ArrayList<ReviewAndRating> getReviewAndRating() {
		return reviewAndRating;
	}

	public ShowStatus getShowStatus() {
		return showStatus;
	}
	
	public Duration getDuration() {
		return duration;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSynopsis(String synopsis) {  
		this.synopsis = synopsis;
	}

	public void setDirector(String director) {  
		this.director = director;
		
	}

	public void setCast(String[] cast) {
		this.cast = cast;
	}
	
	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;	
	}

	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public void setShowStatus(ShowStatus showStatus) {
		this.showStatus = showStatus;
	}
}