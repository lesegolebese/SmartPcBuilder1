

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}