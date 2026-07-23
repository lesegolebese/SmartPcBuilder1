package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.PcBuildItem;
import za.ac.cput.service.PcBuildItemService;

import java.util.List;

@RestController
@RequestMapping("/pcbuilditem")
public class PcBuildItemController {

    private final PcBuildItemService pcBuildItemService;

    @Autowired
    public PcBuildItemController(PcBuildItemService pcBuildItemService) {
        this.pcBuildItemService = pcBuildItemService;
    }

    @PostMapping("/create")
    public ResponseEntity<PcBuildItem> create(@RequestBody PcBuildItem pcBuildItem) {
        PcBuildItem created = pcBuildItemService.create(pcBuildItem);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PcBuildItem> read(@PathVariable String id) {
        PcBuildItem pcBuildItem = pcBuildItemService.read(id);
        if (pcBuildItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pcBuildItem, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PcBuildItem> update(@RequestBody PcBuildItem pcBuildItem) {
        PcBuildItem updated = pcBuildItemService.update(pcBuildItem);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        boolean deleted = pcBuildItemService.delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<PcBuildItem>> getAll() {
        List<PcBuildItem> pcBuildItems = pcBuildItemService.getAll();
        return new ResponseEntity<>(pcBuildItems, HttpStatus.OK);
    }
}