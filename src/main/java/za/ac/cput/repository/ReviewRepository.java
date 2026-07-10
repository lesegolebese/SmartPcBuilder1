
/**
 * ReviewRepository.java
 * Author: Lesego Lebese 222371196
 * Date: 09 July 2026
 */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Review;
import java.util.Optional;
import java.util.List;



@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    // Custom query method example if you want to find reviews by their rating score
    List<Review> findByRating(String rating);
}