package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CompatibilityRule;
import za.ac.cput.repository.CompatibillityRuleRepository;

import java.util.List;


@Service
public class CompatibillityRuleService implements ICompatibillityRuleService {

    private final CompatibillityRuleRepository repository;

    @Autowired
    public CompatibillityRuleService(CompatibillityRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompatibilityRule create(CompatibilityRule rule) {
        return repository.save(rule);
    }

    @Override
    public CompatibilityRule read(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CompatibilityRule update(CompatibilityRule rule) {
        if (repository.existsById(rule.getRuleId())) {
            return repository.save(rule);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return !repository.existsById(id);
        }
        return false;
    }

    @Override
    public List<CompatibilityRule> getAll() {
        return repository.findAll();
    }

    @Override
    public List<CompatibilityRule> findByComponentType1(String componentType1) {
        return repository.findByComponentType1(componentType1);
    }

    @Override
    public List<CompatibilityRule> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    @Override
    public List<CompatibilityRule> findByRuledescriptionContaining(String keyword) {
        return repository.findByRuledescriptionContaining(keyword);
    }

    @Override
    public CompatibilityRule findByRuleId(String ruleId) {
        return repository.findByRuleId(ruleId).orElse(null);
    }
}
