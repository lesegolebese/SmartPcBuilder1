/*
 * ReviewFactory.java
 * Author: Lesego Lebese 222371196
 * Date: 27 June 2026
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;
import java.time.LocalDate;

public class ReviewFactory {

    public static Review createReview(String reviewId, String rating, String comment, LocalDate reviewDate) {
        if (Helper.isNullOrEmpty(reviewId) || Helper.isNullOrEmpty(rating) || reviewDate == null) {
            return null;
        }

        return new Review.Builder()
                .setReviewId(reviewId)
                .setRating(rating)
                .setComment(comment)
                .setReviewDate(reviewDate)
                .build();
    }
}
