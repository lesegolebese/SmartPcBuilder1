package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CompatibilityRule;
import za.ac.cput.service.ICompatibillityRuleService;

import java.util.List;

@RestController
@RequestMapping("/compatibilityrule")
public class CompatibilityRuleController {

    private final ICompatibillityRuleService compatibilityRuleService;

    @Autowired
    public CompatibilityRuleController(ICompatibillityRuleService compatibilityRuleService) {
        this.compatibilityRuleService = compatibilityRuleService;
    }

    @PostMapping("/create")
    public ResponseEntity<CompatibilityRule> create(@RequestBody CompatibilityRule rule) {
        CompatibilityRule created = compatibilityRuleService.create(rule);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<CompatibilityRule> read(@PathVariable String id) {
        CompatibilityRule rule = compatibilityRuleService.read(id);
        if (rule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rule, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CompatibilityRule> update(@RequestBody CompatibilityRule rule) {
        CompatibilityRule updated = compatibilityRuleService.update(rule);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        boolean deleted = compatibilityRuleService.delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CompatibilityRule>> getAll() {
        List<CompatibilityRule> rules = compatibilityRuleService.getAll();
        return new ResponseEntity<>(rules, HttpStatus.OK);
    }

    @GetMapping("/componenttype/{componentType}")
    public ResponseEntity<List<CompatibilityRule>> findByComponentType(@PathVariable String componentType) {
        List<CompatibilityRule> rules = compatibilityRuleService.findByComponentType1(componentType);
        if (rules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rules, HttpStatus.OK);
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<CompatibilityRule>> findByDescription(@PathVariable String description) {
        List<CompatibilityRule> rules = compatibilityRuleService.findByDescription(description);
        if (rules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rules, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<CompatibilityRule>> searchByKeyword(@PathVariable String keyword) {
        List<CompatibilityRule> rules = compatibilityRuleService.findByRuledescriptionContaining(keyword);
        if (rules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rules, HttpStatus.OK);
    }
}
