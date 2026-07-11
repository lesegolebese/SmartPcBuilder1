//
package za.ac.cput.service;

import za.ac.cput.domain.Review;
import java.util.List;

public interface IReviewService extends Service<Review, String> {
    List<Review> getAll();
}
