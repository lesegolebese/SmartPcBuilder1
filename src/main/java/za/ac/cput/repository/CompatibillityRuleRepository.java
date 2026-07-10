/**
 * CompatibillityRuleRepository.java
 * Author: Matinisa Lubisi 222527269
 * Date: 10 July 2026
 */

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.CompatibilityRule;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompatibillityRuleRepository extends JpaRepository<CompatibilityRule, String> {

    List<CompatibilityRule> findByComponentType1(String componentType1);

    List<CompatibilityRule> findByDescription(String description);

    List<CompatibilityRule> findByRuledescriptionContaining(String keyword);


    Optional<CompatibilityRule> findByRuleId(String ruleId);
}
