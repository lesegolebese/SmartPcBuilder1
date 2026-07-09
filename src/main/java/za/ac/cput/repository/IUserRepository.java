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

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    // Spring Boot automatically gives you:
    // save()       -> replaces create() and update()
    // findById()   -> replaces read()
    // deleteById() -> replaces delete()
    // findAll()    -> replaces getAll()
}
