package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.IAdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final IAdminService IAdminService;

    @Autowired
    public AdminController(IAdminService IAdminService) {
        this.IAdminService = IAdminService;
    }

    @PostMapping("/create")
    public ResponseEntity<Admin> create(@RequestBody Admin admin) {
        Admin created = IAdminService.create(admin);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Admin> read(@PathVariable String id) {
        Admin admin = IAdminService.read(id);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> update(@RequestBody Admin admin) {
        Admin updated = IAdminService.update(admin);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        boolean deleted = IAdminService.delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Admin>> getAll() {
        List<Admin> admins = IAdminService.getAll();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Admin> findByEmail(@PathVariable String email) {
        Admin admin = IAdminService.findByEmail(email);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestParam String email, @RequestParam String password) {
        Admin admin = IAdminService.findByEmailAndPassword(email, password);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
}
