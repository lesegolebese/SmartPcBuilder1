/**
 * OrderRepository.java
 * Author: Coben Maistry 220235686
 * Date: 12 July 2026
 */

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
