/* Review.java
   Review POJO entity implementing Builder Pattern
   Author: Lesego Lebese (222371196)
   Date: 21 June 2026 */

package za.ac.cput.domain;
import java.time.LocalDate;

public class Review {
    private String reviewId;
    private String rating;
    private String comment;
    private LocalDate reviewDate;

    protected Review() {}

    protected Review(Builder builder) {
        this.reviewId = builder.reviewId;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.reviewDate = builder.reviewDate;
    }

    public String getReviewId() { return reviewId; }
    public String getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDate getReviewDate() { return reviewDate; }

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

        public Builder copy(Review review) {
            this.reviewId = review.reviewId;
            this.rating = review.rating;
            this.comment = review.comment;
            this.reviewDate = review.reviewDate;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}
