

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}