package boundary;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Scanner;

import entity.*;

/*
 * This class is the boundary class for the show time edit view
 */
public class MovieTimeEditView {
    /*
     * This method is used to display the add show time view
     */
    public static LocalDateTime addMovieTime(){
        Scanner sc = new Scanner(System.in);

        try{
            Cineplex cineplex = MenuView.getLabelledItem("Select a cineplex: ", DatabaseManager.getDataBase().getCineplexList());
            
            ArrayList<Cinema> cinemaList = cineplex.getCinemas();
            Cinema cinema = MenuView.getLabelledItem("Select a cinema: ", cinemaList);
            Movie movie = MenuView.getLabelledItem("Select a movie: ", DatabaseManager.getDataBase().getMovieList());

            System.out.println("Enter movie start date and time (dd/MM/yyyy HH:mm): ");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            
            LocalDateTime movieTime;
            while(true){
                try{
                    movieTime = LocalDateTime.parse(sc.nextLine(), format);
                } catch (DateTimeParseException e){
                    System.out.println("Invalid date and time format. Please try again.");
                }
            }

            Duration duration = movie.getDuration();
            if(isClash(movieTime, duration, cinema)){
                System.out.println("Movie time clashes with another movie time. Please try again.");
            }
            else{
                cinema.addMovieTime(movieTime, movie);
            }

        } finally{
            sc.close();
        }
    }
    /*
     * This method is used to display the remove show time view
     */
    public static void removeMovieTime() {
        Scanner sc = new Scanner(System.in);

        try{
            Cineplex cineplex = MenuView.getLabelledItem("Select a cineplex: ", DatabaseManager.getDataBase().getCineplexList());
            
            ArrayList<Cinema> cinemaList = cineplex.getCinemas();
            Cinema cinema = MenuView.getLabelledItem("Select a cinema: ", cinemaList);
            MovieTime movieTime = MenuView.getLabelledItem("Select a movie time to remove: ", cinema.getMovieTimes());
            movieTime.remove();

        } finally{
            sc.close();
        }
    }
    /*
     * This method is used to update the show time of a movie
     */
    public static void updateMovieTime(){
        Scanner sc = new Scanner(System.in);

        try{
            Cineplex cineplex = MenuView.getLabelledItem("Select a cineplex: ", DatabaseManager.getDataBase().getCineplexList());
            
            ArrayList<Cinema> cinemaList = cineplex.getCinemas();
            Cinema cinema = MenuView.getLabelledItem("Select a cinema: ", cinemaList);
            MovieTime movieTime = MenuView.getLabelledItem("Select a movie time to update: ", cinema.getMovieTimes());

            int option = MenuView.getMenuOption(
                "Select an option: ",
                "Movie date and time",
                "Movie"
            );

            switch(option){
                case 1:
                    System.out.println("Enter movie start date and time (dd/MM/yyyy HH:mm): ");
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime mt;
                    while(true){
                        try{
                            mt = LocalDateTime.parse(sc.nextLine(), format);
                        } catch (DateTimeParseException e){
                            System.out.println("Invalid date and time format. Please try again.");
                        }
                    }
        
                    Duration duration = movieTime.getMovie().getDuration();
                    if(isClash(mt, duration, cinema)){
                        System.out.println("Movie time clashes with another movie time. Please try again.");
                    }
                    else{
                        movieTime.setMovieDateTime(mt);
                    }
                    break;
                case 2:
                    Movie movie = MenuView.getLabelledItem("Select a movie: ", DatabaseManager.getDataBase().getMovieList());
                    movieTime.setMovie(movie);
                    break;
            }

        } finally{
            sc.close();
        }
    }
    /*
     * This method is used to check if the movie time clashes with another movie time
     */
    public static boolean isClash(LocalDateTime movieTime, Duration duration, Cinema cinema) {
        ArrayList<MovieTime> movieTimeList = cinema.getMovieTimes();
        
        //need to check logic again
        for(MovieTime mt : movieTimeList){
            if(movieTime.isAfter(mt.getMovieDateTime().plus(duration)) && movieTime.isBefore(mt.getMovieDateTime())){
                return true;
            }
        }
        return false;
    }
}

