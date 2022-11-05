package boundary;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

import entity.*;

/*
 * This class is the boundary class for the movie edit view
 */
public class MovieEditView {
    /*
     * This method is used to display the add movie view
     */
    public static void addMovie() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter the title of the movie: ");
            String title = sc.nextLine();
            System.out.println("Enter the synopsis of the movie: ");
            String synopsis = sc.nextLine();
            System.out.println("Enter the director of the movie: ");
            String director = sc.nextLine();

            // change to array list?
            System.out.print("Enter the number of casts: ");
            String[] castNames = new String[sc.nextInt()];
            sc.nextLine();
            for (int i = 0; i < castNames.length; i++) {
                System.out.println("Enter the name of cast " + (i + 1) + ": ");
                castNames[i] = sc.nextLine();
            }

            System.out.println("Enter the duration of the movie in minutes: ");
            int durationMovie = sc.nextInt();
            Duration duration = Duration.ofMinutes(durationMovie);

            // waiting for MenuView to be done
            MovieRating movieRating = MenuView.getItemName("Release rating: ", MovieRating.values());
            MovieType movieType = MenuView.getItemName("Movie type: ", MovieType.values());
            ShowStatus showStatus = MenuView.getItemName("Show status: ", ShowStatus.values());

            Movie movie = new Movie(title, synopsis, director, castNames, showStatus, movieRating, movieType, duration);
            DatabaseManager.getDataBase().getMovieList().add(movie);

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    /*
     * This method is used to display the remove movie view
     */
    public static void removeMovie() {
        ArrayList<Movie> movieList = DatabaseManager.getDataBase().getMovieList();
        Movie movie = MenuView.getItemName("Select a movie to remove: ", movieList);
        movie.setShowStatus(ShowStatus.END_OF_SHOWING);
    }

    /*
     * This method is used to display the update movie view
     */
    public static void updateMovie() {
        ArrayList<Movie> movieList = DatabaseManager.getDataBase().getMovieList();
        Movie movie = MenuView.getItemName("Select a movie to update: ", movieList);

        int option = MenuView.getMenuOption(
                "What do you want to update?",
                "Title",
                "Synopsis",
                "Director",
                "Cast",
                "Duration",
                "Movie rating",
                "Movie type",
                "Show status");

        Scanner sc = new Scanner(System.in);

        try {
            switch (option) {
                case 1:
                    System.out.println("Enter the new title: ");
                    movie.setTitle(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Enter the new synopsis: ");
                    movie.setSynopsis(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Enter the new director: ");
                    movie.setDirector(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Enter the number of casts: ");
                    String[] castNames = new String[sc.nextInt()];
                    sc.nextLine();
                    for (int i = 0; i < castNames.length; i++) {
                        System.out.println("Enter the name of cast " + (i + 1) + ": ");
                        castNames[i] = sc.nextLine();
                    }
                    movie.setCast(castNames);
                    break;
                case 5:
                    System.out.println("Enter the new duration in minutes: ");
                    int durationMovie = sc.nextInt();
                    Duration duration = Duration.ofMinutes(durationMovie);
                    movie.setDuration(duration);
                    break;
                case 6:
                    movie.setMovieRating(MenuView.getItemName("Release rating: ", MovieRating.values()));
                    break;
                case 7:
                    movie.setMovieType(MenuView.getItemName("Movie type: ", MovieType.values()));
                    break;
                case 8:
                    movie.setShowStatus(MenuView.getItemName("Show status: ", ShowStatus.values()));
                    break;
            }

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }
}
