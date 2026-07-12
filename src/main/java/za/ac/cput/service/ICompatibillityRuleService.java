package za.ac.cput.service;

import za.ac.cput.domain.CompatibilityRule;

import java.util.List;

public interface ICompatibillityRuleService extends IService<CompatibilityRule, String> {
    List<CompatibilityRule> getAll();
    List<CompatibilityRule> findByComponentType1(String componentType1);
    List<CompatibilityRule> findByDescription(String description);
    List<CompatibilityRule> findByRuledescriptionContaining(String keyword);
    CompatibilityRule findByRuleId(String ruleId);
}
