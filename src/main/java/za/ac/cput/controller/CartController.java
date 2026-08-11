package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create")
    public ResponseEntity<Cart> create(@RequestBody Cart cart) {
        Cart created = cartService.create(cart);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Cart> read(@PathVariable Long id) {
        Cart cart = cartService.read(id);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> update(@RequestBody Cart cart) {
        Cart updated = cartService.update(cart);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean deleted = cartService.delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Cart>> getAll() {
        List<Cart> carts = cartService.getAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
}
