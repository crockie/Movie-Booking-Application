package Entity;

import java.io.Serializable;

// This class contains all information on reviews and ratings
public class ReviewAndRating implements Serializable, ItemName {
    /**
     * The serialisation version number <-- TO BE REVISITED
     */
    private static final long serialVersionUID = 1725162345547776214L;

    // Minimum reviewer rating value
    public static final int MINIMUM_RATING = 1;

    // Maximum reviewer rating value
    public static final int MAXIMUM_RATING = 5;

    // The user who made the review rating
    private User user;
    // The review
    private String review;

    // The rating
    private Integer rating;

    private ReviewAndRating() {}

    public static ReviewAndRating createReviewRating(User user, String review, int rating) {
        ReviewAndRating reviewRating = new ReviewAndRating();

        reviewRating.user = user;
        reviewRating.review = review;
        reviewRating.rating = rating;

        if (rating >= MINIMUM_RATING && rating <= MAXIMUM_RATING)
            return reviewRating;
        else
            return null;
    }

    // Returns user that made the review and rating
    public User getUser() {
        return user;
    }

    // Returns review
    public String getReview() {
        return review;
    }

    // Returns rating
    public int getRating() {
        return rating;
    }

    @Override
    public String nameToString() {
        return review + " (" + rating + "/5) —— " + user.getName();
    }
}
