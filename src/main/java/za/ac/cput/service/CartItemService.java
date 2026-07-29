//
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CartItem;
import za.ac.cput.repository.CartItemRepository;

import java.util.List;

@Service
public class CartItemService implements ICartItemService {

    private final CartItemRepository repository;

    @Autowired
    public CartItemService(CartItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public CartItem create(CartItem cartItem) {
        return repository.save(cartItem);
    }

    @Override
    public CartItem read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CartItem update(CartItem cartItem) {
        if (repository.existsById(cartItem.getCartItemId())) {
            return repository.save(cartItem);
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
    public List<CartItem> getAll() {
        return repository.findAll();
    }
}
