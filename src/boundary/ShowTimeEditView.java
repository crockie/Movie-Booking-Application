package boundary;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.*;

/**
 * This class edits the show time of movie
 */
@SuppressWarnings("resource")
public class ShowTimeEditView {
    /**
     * This method displays the add show time view
     */
    public static void addMovieTime() {
        Scanner sc = new Scanner(System.in);

        Cineplex cineplex = MenuView.getItemName("Select a cineplex: ",
                DatabaseManager.getDataBase().getCineplexList());

        ArrayList<Cinema> cinemaList = cineplex.getCinemas();
        Cinema cinema = MenuView.getItemName("Select a cinema: ", cinemaList);
        Movie movie = MenuView.getItemName("Select a movie: ", DatabaseManager.getDataBase().getMovieList());

        System.out.println("Enter movie start date and time (dd/MM/yyyy HH:mm): ");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime movieTime;
        while (true) {
            try {
                movieTime = LocalDateTime.parse(sc.nextLine(), format);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format. Please try again.");
            }
        }

        Duration duration = movie.getDuration();

        if (isClash(movieTime, duration, cinema, null)) {
            System.out.println("Movie time clashes with another movie time. Please try again.");
        } else {
            cinema.addMovieTime(movieTime, movie);
        }

    }

    /**
     * This method displays the remove show time view
     */
    public static void removeMovieTime() {
        Cineplex cineplex = MenuView.getItemName("Select a cineplex: ",
                DatabaseManager.getDataBase().getCineplexList());

        ArrayList<Cinema> cinemaList = cineplex.getCinemas();
        Cinema cinema = MenuView.getItemName("Select a cinema: ", cinemaList);
        MovieTime movieTime = MenuView.getItemName("Select a movie time to remove: ", cinema.getMovieTimes());
        movieTime.remove();

    }

    /**
     * This method updates the show time of a movie
     */
    public static void updateMovieTime() {
        Scanner sc = new Scanner(System.in);

        Cineplex cineplex = MenuView.getItemName("Select a cineplex: ",
                DatabaseManager.getDataBase().getCineplexList());

        ArrayList<Cinema> cinemaList = cineplex.getCinemas();
        Cinema cinema = MenuView.getItemName("Select a cinema: ", cinemaList);
        MovieTime movieTime = MenuView.getItemName("Select a movie time to update: ", cinema.getMovieTimes());

        int option = MenuView.getMenuOption(
                "Select an option: ",
                "Movie date and time",
                "Movie");

        switch (option) {
            case 1:
                System.out.println("Enter movie start date and time (dd/MM/yyyy HH:mm): ");
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime mt;
                while (true) {
                    try {
                        mt = LocalDateTime.parse(sc.nextLine(), format);
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date and time format. Please try again.");
                    }
                }

                Duration duration = movieTime.getMovie().getDuration();

                if (isClash(mt, duration, cinema, movieTime)) {
                    System.out.println("Movie time clashes with another movie time. Please try again.");
                } else {
                    movieTime.setMovieDateTime(mt);
                }
                break;
            case 2:
                Movie movie = MenuView.getItemName("Select a movie: ",
                        DatabaseManager.getDataBase().getMovieList());
                movieTime.setMovie(movie);
                break;
        }

    }

    /**
     * This method checks if the movie time clashes with another movie time
     * @param movieTime The movie time to check
     * @param duration The duration of the movie
     * @param cinema The cinema to check
     * @param currentMovieTime The current movie time
     * @return true if the movie time clashes with another movie time, else it is
     *         false
     */
    public static boolean isClash(LocalDateTime movieTime, Duration duration, Cinema cinema, MovieTime currentMovieTime) {
        for (MovieTime movieStartTime: cinema.getMovieTimes()) {
			LocalDateTime movieEndTime = movieTime.plus(duration);
			LocalDateTime oldStartMovieDateTime = movieStartTime.getMovieDateTime();
			LocalDateTime oldEndMovieDateTime = oldStartMovieDateTime.plus(movieStartTime.getMovie().getDuration());
			
			boolean isClash = (
				(oldStartMovieDateTime.isBefore(movieEndTime) && !oldStartMovieDateTime.isEqual(movieEndTime)) &&
				(movieTime.isBefore(oldEndMovieDateTime) && !movieTime.isEqual(oldEndMovieDateTime)) &&
				movieStartTime != currentMovieTime
			);
			
			if (isClash)
				return true;
		}
        return false;
    }
}
