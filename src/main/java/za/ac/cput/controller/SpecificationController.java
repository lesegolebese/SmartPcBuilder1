package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Specification;
import za.ac.cput.service.SpecificationService;
import java.util.*;

@RestController
@RequestMapping("/specification")
public class SpecificationController {

    private final SpecificationService specificationService;

    @Autowired
    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @PostMapping("/create")
    public ResponseEntity<Specification> create(@RequestBody Specification specification) {
        Specification created = specificationService.create(specification);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Specification> read(@PathVariable Long id) {
        Specification specification = specificationService.read(id);
        if (specification == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(specification, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Specification> update(@RequestBody Specification specification) {
        Specification updated = specificationService.update(specification);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean deleted = specificationService.delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Specification>> getAll() {
        List<Specification> specifications = specificationService.getAll();
        return new ResponseEntity<>(specifications, HttpStatus.OK);
    }
}