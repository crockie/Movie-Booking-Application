package Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Hall{
    private Cinema cinema;
    private String name;
    private boolean[][] seatLayout;
    private ArrayList<MovieTime> movieTimes = new ArrayList<MovieTime>();

    public Hall(String name, boolean[][] seatLayout){
        this.name = name;
        this.seatLayout = seatLayout;
    }

    public void addMovieTime(Cinema cinema, LocalDateTime movieDateTime, Movie movie){
        MovieTime movieTime = new MovieTime(cinema, this, movieDateTime, movie);
        this.movieTimes.add(movieTime);
    }

    public String getName(){
        return this.name;
    }

    public Cinema getCinema(){
        return this.cinema;
    }

    public boolean[][] getSeatLayout(){
        return this.seatLayout;
    }
    public ArrayList<MovieTime> getMovieTimes(){
            return this.movieTimes;
    }

}
