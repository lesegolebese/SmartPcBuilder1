
/**
 * ReviewRepository.java
 * Author: Lesego Lebese 222371196
 * Date: 09 July 2026
 */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Review;

@Repository
public interface IReviewRepository extends JpaRepository<Review, String> {
    // Spring Data JPA completely handles your CRUD logic automatically!
    // save()       -> replaces create() and update()
    // findById()   -> replaces read()
    // deleteById() -> replaces delete()
    // findAll()    -> replaces getAll()
}