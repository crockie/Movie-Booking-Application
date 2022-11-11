package boundary;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Customer;
import entity.DataBase;
import entity.DatabaseManager;
import entity.Movie;
import entity.ReviewAndRating;
import entity.ShowStatus;

/**
 * This class displays movie details to user
 */
@SuppressWarnings("resource")
public class MovieView {
    /**
     * This method displays all movies with details in database
     * @param isCustomer Customer query the available movies
     */
    public static void getAllMoviesView(boolean isCustomer) {
        DataBase database = DatabaseManager.getDataBase();
        ArrayList<Movie> movieList = database.getMovieList();
        int i = 1;

        if (isCustomer) {
            for (Movie movie : movieList) {
                if (movie.getShowStatus() != ShowStatus.END_OF_SHOWING) {
                    System.out.println("Movie #" + i++);
                    System.out.println("Title: " + movie.getTitle());
                    System.out.println("Showing status: " + movie.getShowStatus().nameToString());
                    System.out.println();
                }
            }
        } else {
            for (Movie movie : movieList) {
                System.out.println("Movie #" + i++);
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Showing status: " + movie.getShowStatus().nameToString());
                System.out.println();
            }
        }
    }

    /**
     * This method takes user's query for movie and display the specific movie
     * details
     * 
     * @param movie The movie that user query
     */
    public static void getMovieView(Movie movie) {
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Showing status: " + movie.getShowStatus().nameToString());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Director: " + movie.getDirector());
        int i = 1;
        for (String cast : movie.getCast()) {
            System.out.println("Cast " + i + ": " + cast);
            i++;
        }

        System.out.println("Overall reviewer rating: " + movie.getAverageRating());
        System.out.println("Past reviews and reviewers' ratings: ");
        for (ReviewAndRating review : movie.getReviewAndRating()) {
            System.out.println("Reviewer " + review.getCustomer().getName());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Review: " + review.getReview());
        }
    }

    /**
     * This method adds review and rating to a movie
     * 
     * @param movie    The movie that user wants to review
     * @param customer The customer
     */
    public static void addMovieReview(Movie movie, Customer customer) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Rating: ");
            int rating = sc.nextInt();
            sc.nextLine();
            System.out.print("Review: ");
            String review = sc.nextLine();

            ReviewAndRating reviewAndRating = ReviewAndRating.createReviewRating(customer, review, rating);

            movie.getReviewAndRating().add(reviewAndRating);
            System.out.println("Review and rating added!");
        } catch (Exception e) {
            System.out.println("Invalid input!");
        }

    }
}
