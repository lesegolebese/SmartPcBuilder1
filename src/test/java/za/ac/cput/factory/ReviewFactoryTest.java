/* Address.java
   Address POJO entity implementing Builder Pattern
   Author: Lesego Lebese (222371196)
   Date: 27 June 2026 *///


package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Review;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {
    private Review review;

    @BeforeEach
    void setUp() {
        review = ReviewFactory.createReview("Rev1", "5 Stars", "Excellent built-quality on the components!", LocalDate.now());
    }

    @Test
    void testCreateReview() {
        assertNotNull(review);
        assertEquals("Rev1", review.getReviewId());
        System.out.println(review.toString());
    }

    @Test
    void testCreateReviewWithMissingData() {
        Review invalidReview = ReviewFactory.createReview("", "", "No rating given", null);
        assertNull(invalidReview, "Factory should return null for empty reviewId, rating, or null date");
        System.out.println("Validation check passed: Invalid review is null.");
    }

    @Test
    void testIdentity() {
        assertInstanceOf(Review.class, review);
    }
}