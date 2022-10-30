package entity;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cinema {
    private String name;
    private CinemaClass cinemaClass;
    private boolean[][] seatLayout;
    private ArrayList<MovieTime> movieTimes = new ArrayList<MovieTime>();

    public Cinema(String name, CinemaClass cinemaClass, boolean[][] seatLayout){
        this.name = name;
        this.cinemaClass = cinemaClass;
        this.seatLayout = seatLayout;
    }

    public void addMovieTime(LocalDateTime movieDateTime, Movie movie){
        MovieTime movieTime = new MovieTime(this, movieDateTime, movie);
        this.movieTimes.add(movieTime);
    }

    public String getName(){
        return this.name;
    }
    public CinemaClass getCinemaClass(){
        return this.cinemaClass;
    }
    public boolean[][] getSeatLayout(){
        return this.seatLayout;
    }
    public ArrayList<MovieTime> getMovieTimes(){
        return this.movieTimes;
}
}
