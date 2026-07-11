/**
 * PcBuildItemRepository.java
 * Author: Mnelisi Mabona 222062088
 * Date: 09 July 2026
 */

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.PcBuildItem;

@Repository
public interface PcBuildItemRepository extends JpaRepository<PcBuildItem, String> {
}