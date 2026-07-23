package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.PcBuild;
import za.ac.cput.service.PcBuildService;

import java.util.List;

@RestController
@RequestMapping("/pcbuild")
public class PcBuildController {

    private final PcBuildService pcBuildService;

    @Autowired
    public PcBuildController(PcBuildService pcBuildService) {
        this.pcBuildService = pcBuildService;
    }

    @PostMapping("/create")
    public ResponseEntity<PcBuild> create(@RequestBody PcBuild pcBuild) {
        PcBuild created = pcBuildService.create(pcBuild);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PcBuild> read(@PathVariable String id) {
        PcBuild pcBuild = pcBuildService.read(id);
        if (pcBuild == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pcBuild, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PcBuild> update(@RequestBody PcBuild pcBuild) {
        PcBuild updated = pcBuildService.update(pcBuild);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        boolean deleted = pcBuildService.delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<PcBuild>> getAll() {
        List<PcBuild> pcBuilds = pcBuildService.getAll();
        return new ResponseEntity<>(pcBuilds, HttpStatus.OK);
    }
}