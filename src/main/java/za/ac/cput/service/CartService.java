//
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Cart;
import za.ac.cput.repository.CartRepository;

import java.util.List;


@Service
public class CartService implements ICartService {

    private final CartRepository repository;


    @Autowired
    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cart create(Cart cart) {
        return repository.save(cart);
    }

    @Override
    public Cart read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Cart update(Cart cart) {
        if (repository.existsById(cart.getCartId())) {
            return repository.save(cart);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return !repository.existsById(id);
        }
        return false;
    }

    @Override
    public List<Cart> getAll() {
        return repository.findAll();
    }
}
