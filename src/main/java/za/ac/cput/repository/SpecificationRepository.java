
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Specification;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {
}