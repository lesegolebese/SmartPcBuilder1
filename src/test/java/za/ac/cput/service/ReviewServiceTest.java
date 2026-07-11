package za.ac.cput.service;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.ReviewFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewServiceTest {

    @Autowired
    private ReviewService service;

    private static Review review;

    @BeforeAll
    static void init() {
        review = ReviewFactory.createReview(
                "R555",
                4,
                "Great system setup, processing speeds are incredibly fluid."
        );
        assertNotNull(review);
    }

    @Test
    @Order(1)
    void testCreate() {
        Review created = service.create(review);
        assertNotNull(created);
        System.out.println("Service Created Review: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        Review read = service.read(review.getReviewId());
        assertNotNull(read);
        System.out.println("Service Read Review: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Review updatedReview = new Review.Builder().copy(review).setComment("Brilliant processing speeds!").build();
        Review updated = service.update(updatedReview);
        assertNotNull(updated);
        assertEquals("Brilliant processing speeds!", updated.getComment());
        System.out.println("Service Updated Review: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<Review> list = service.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        System.out.println("Total Reviews in Service: " + list.size());
    }

    @Test
    @Order(5)
    void testDelete() {
        boolean deleted = service.delete(review.getReviewId());
        assertTrue(deleted);
        Review read = service.read(review.getReviewId());
        assertNull(read);
        System.out.println("Review successfully deleted from Service context.");
    }
}