/* Review.java
   Review POJO entity implementing Builder Pattern
   Author: Lesego Lebese (222371196)
   Date: 22 June 2026 */

package za.ac.cput.domain;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Reviews")
public class Review {

    @Id
    private String reviewId;

    private String rating;
    private String comment;
    private LocalDate reviewDate; // Aligned with LocalDate in factory

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Review() {
    }

    private Review(Builder builder) {
        this.reviewId = builder.reviewId;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.reviewDate = builder.reviewDate;
        this.user = builder.user;
    }

    // GETTERS
    public String getReviewId() { return reviewId; }
    public String getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDate getReviewDate() { return reviewDate; }
    public User getUser() { return user; }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId='" + reviewId + '\'' +
                ", rating='" + rating + '\'' +
                ", comment='" + comment + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }

    public static class Builder {
        private String reviewId;
        private String rating;
        private String comment;
        private LocalDate reviewDate;
        private User user;

        public Builder setReviewId(String reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder setRating(String rating) {
            this.rating = rating;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setReviewDate(LocalDate reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder copy(Review review) {
            this.reviewId = review.reviewId;
            this.rating = review.rating;
            this.comment = review.comment;
            this.reviewDate = review.reviewDate;
            this.user = review.user;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}