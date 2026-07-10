/**
 * AdminRepository.java
 * Author: Matinisa Lubisi 222527269
 * Date: 10 July 2026
 */

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Admin;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByEmail(String email);

    List<Admin> findByFirstName(String firstName);

    List<Admin> findByLastName(String lastName);

    Optional<Admin> findByEmailAndPassword(String email, String password);
}
