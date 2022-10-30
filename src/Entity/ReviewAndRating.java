package Entity;

import java.io.Serializable;

/**
* This class contains all information on reviews and ratings
*/
public class ReviewAndRating implements Serializable, ItemName {
    /**
     * The serialisation version number <-- TO BE REVISITED
     */
    private static final long serialVersionUID = 1725162345547776214L;

    /**
    * The minimum reviewer rating value
    */
    public static final int MINIMUM_RATING = 1;
    
    /**
    * The maximum reviewer rating value
    */
    public static final int MAXIMUM_RATING = 5;
    
    /**
    * The customer who made the review rating
    */
    private Customer customer;
    
    /**
    * The review
    */
    private String review;
    
    /**
    * The rating
    */
    private Integer rating;

    /**
    * Creates a {@code ReviewAndRating} object
    */
    private ReviewAndRating() {}

    /**
    * Creates a {@code ReviewAndRating} object with the given customer, review and rating
    * @param customer the customer who made the review rating
    * @param review the review
    * @param rating the rating
    * @return the {@code ReviewAndRating} object if it's valid, null otherwise
    */   
    public static ReviewAndRating createReviewRating(Customer customer, String review, int rating) {
        ReviewAndRating reviewRating = new ReviewAndRating();

        reviewRating.customer = customer;
        reviewRating.review = review;
        reviewRating.rating = rating;

        if (rating <= MAXIMUM_RATING && rating >= MINIMUM_RATING)
            return reviewRating;
        else
            return null;
    }

    /**
    * Returns the customer who made the review rating
    * @return the customer who made the review rating
    */ 
    public Customer getCustomer() {
        return customer;
    }

    /**
    * Returns the review
    * @return the review
    */
    public String getReview() {
        return review;
    }

    /**
    * Returns the rating
    * @return the rating
    */
    public int getRating() {
        return rating;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public String nameToString() {
        return review + " (" + rating + "/5) —— " + customer.getName();
    }
}
