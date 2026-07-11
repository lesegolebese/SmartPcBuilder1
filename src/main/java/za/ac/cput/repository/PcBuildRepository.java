
/**
 * PcBuildRepository.java
 * Author: Mnelisi Mabona 222062088
 * Date: 09 July 2026
 */

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.PcBuild;

@Repository
public interface PcBuildRepository extends JpaRepository<PcBuild, String> {
}