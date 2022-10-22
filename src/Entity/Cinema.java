package Entity;
import java.util.ArrayList;

public class Cinema {
    private String name;
    private CinemaClass cinemaClass;
    private ArrayList<Hall> halls = new ArrayList<Hall>();

    public Cinema(String name, CinemaClass cinemaClass){
        this.name = name;
        this.cinemaClass = cinemaClass;
    }

    public void createHalls(String hallName, boolean[][] seatLayout){
        Hall hall = new Hall(hallName, seatLayout);
        this.halls.add(hall);
    }

    public String getName(){
        return this.name;
    }
    public CinemaClass getCinemaClass(){
        return this.cinemaClass;
    }
    public ArrayList<Hall> getHalls(){
        return this.halls;
    }
}
