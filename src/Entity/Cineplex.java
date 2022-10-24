package Entity;
import java.util.ArrayList;

public class Cineplex {
    private String name;
    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();

    public Cineplex(String name){
        this.name = name;
    }
    
    public void addCinema(String cinemaName, CinemaClass cinemaClass){
        Cinema cinema = new Cinema(cinemaName, cinemaClass);
        this.cinemas.add(cinema);
    }

    public ArrayList<MovieTime> getMovieTimes(){
        ArrayList<MovieTime> movieTimes = new ArrayList<MovieTime>();

        for(Cinema cinema: cinemas){
            for(Hall hall: cinema.getHalls()){
                movieTimes.addAll(hall.getMovieTimes());
            }
        }
        return movieTimes;
    }

    
    public ArrayList<Cinema> getCinemas(){
        return cinemas;
    }

    public String getName(){
        return name;
    }
}
