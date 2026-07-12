package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Address;
import za.ac.cput.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public ResponseEntity<Address> create(@RequestBody Address address) {
        Address created = addressService.create(address);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Address> read(@PathVariable Long id) {
        Address address = addressService.read(id);
        if (address == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Address> update(@RequestBody Address address) {
        Address updated = addressService.update(address);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean deleted = addressService.delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Address>> getAll() {
        List<Address> addresses = addressService.getAll();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
}