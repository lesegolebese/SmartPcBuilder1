/**
 * UserRepository.java
 * Author: Lesego Lebese 222371196
 * Date: 09 July 2026
 */
package za.ac.cput.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.User;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Custom query method to look up a user by their unique email address
    Optional<User> findByEmail(String email);

    // Custom query method to look up a user by their phone number
    Optional<User> findByPhoneNumber(String phoneNumber);

    // Custom query method to find users by their last name
    List<User> findByLastName(String lastName);
}
