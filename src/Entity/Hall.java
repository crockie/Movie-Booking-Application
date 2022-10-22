package Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Hall{
    private String name;
    private boolean[][] seatLayout;
    private ArrayList<MovieTime> movieTimes = new ArrayList<MovieTime>();

    public Hall(String name, boolean[][] seatLayout){
        this.name = name;
        this.seatLayout = seatLayout;
    }

    public void addMovieTime(LocalDateTime movieDateTime,Movie movie){
        MovieTime movieTime = new MovieTime(this, movieDateTime, movie);
        this.movieTimes.add(movieTime);
    }

    public String getName(){
        return this.name;
    }
    public boolean[][] getSeatLayout(){
        return this.seatLayout;
    }
    public ArrayList<MovieTime> getMovieTimes(){
            return this.movieTimes;
    }

}
