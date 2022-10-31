package boundary;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import entity.Cineplex;
import entity.DataBase;
import entity.DatabaseManager;
import entity.Movie;
import entity.MovieTime;
import entity.ShowStatus;

/**
 * This class shows all showtimes
 */
public class ShowtimeView {

    /**
     * This method displays all showtimes for all 'PREVIEW' and 'NOW SHOWING' movie
     * in all cineplexes
     */
    public static void getShowtimeView() {
        DataBase database = DatabaseManager.getDataBase();
        // Access database to get cineplex
        List<Cineplex> cineplexList = database.getCineplexList();

        for (Cineplex cineplex : cineplexList) {
            System.out.println("The name of the cineplex is: " + cineplex.getName());

            // Get showtimes in each cineplex
            List<MovieTime> movieTimeList = cineplex.getMovieTimes();
            // Group showtime with the particular movie
            Map<Movie, List<MovieTime>> movieTime = movieTimeList.stream()
                    .collect(Collectors.groupingBy(MovieTime::getMovie));

            for (Map.Entry<Movie, List<MovieTime>> movieAndTime : movieTime.entrySet()) {
                Movie movie = movieAndTime.getKey();

                ShowStatus movieShowStatus = movie.getShowStatus();
                if (movieShowStatus == ShowStatus.NOW_SHOWING || movieShowStatus == ShowStatus.PREVIEW) {
                    List<MovieTime> showTimeList = movieAndTime.getValue();
                    System.out.println("The name of the movie is: " + movie.getTitle());

                    // Sort the date and time when a showtime is added

                    for (MovieTime showtime : showTimeList) {
                        // Not done...Waiting for method to be created in MovieTime class
                        System.out.println(showtime);
                    }
                }
            }
        }
    }
}
