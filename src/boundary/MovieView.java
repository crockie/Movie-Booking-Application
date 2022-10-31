package boundary;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Customer;
import entity.DataBase;
import entity.DatabaseManager;
import entity.Movie;
import entity.ReviewAndRating;

/**
 * Display movie details to user
 */
public class MovieView {
    /**
     * Display all movies with details in database
     */
    public static void getAllMoviesView() {
        DataBase database = DatabaseManager.getDataBase();
        ArrayList<Movie> movieList = database.getMovieList();

        for (Movie movie : movieList) {
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Showing status: " + movie.getShowStatus().nameToString());
            System.out.println("Synopsis: " + movie.getSynopsis());
            System.out.println("Director: " + movie.getDirector());
            for (String cast : movie.getCast())
                System.out.println("Casts: " + cast);
            System.out.println("Overall reviewer rating: " + movie.getAverageRating());
            System.out.println("Past reviews and reviewers' ratings: ");
            for (ReviewAndRating review : movie.getReviewAndRating()) {
                // System.out.println("User " + review.getUser());
                System.out.println("Rating: " + review.getRating());
                System.out.println("Review: " + review.getReview());
            }

        }
    }

    /**
     * Take user's query for movie and display the specific movie details
     * 
     * @param movie The movie that user query
     */
    public static void getMovieView(Movie movie) {
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Showing status: " + movie.getShowStatus().nameToString());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Director: " + movie.getDirector());
        for (String cast : movie.getCast())
            System.out.println("Casts: " + cast);
        System.out.println("Overall reviewer rating: " + movie.getAverageRating());
        System.out.println("Past reviews and reviewers' ratings: ");
        for (ReviewAndRating review : movie.getReviewAndRating()) {
            // System.out.println("User " + review.getUser());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Review: " + review.getReview());
        }
    }

    /**
     * For user to add review and rating
     * 
     * @param movie The movie that user wants to review
     * @param user  The user
     */
    public static void addMovieReview(Movie movie, Customer customer) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Rating: ");
            int rating = sc.nextInt();
            System.out.print("Review: ");
            String review = sc.nextLine();

            ReviewAndRating reviewAndRating = ReviewAndRating.createReviewRating(customer, review, rating);

            movie.getReviewAndRating().add(reviewAndRating);
            System.out.println("Review and rating added!");
        } finally {
            sc.close();
        }

    }
}
