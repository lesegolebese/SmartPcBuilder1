package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CartFactoryTest {
    private Cart cart;

    private LocalDate createdDate;


    @BeforeEach
    void setUp() {
        createdDate = LocalDate.now();
        cart = CartFactory.createCart(1L, createdDate);
    }

    @Test
    void testCreateCart() {
        assertNotNull(cart);
        assertEquals(1L, cart.getCartId());
        assertEquals(createdDate, cart.getCreatedDate());
        System.out.println(cart.toString());
    }

    @Test
    void testCreateCartWithNullCartId() {
        Cart cart = CartFactory.createCart(null, LocalDate.now());
        assertNotNull(cart, "Factory should create cart with null cartId (auto-generated)");
        System.out.println("Validation check passed: Cart with null id is created for auto-generation.");
    }

    @Test
    void testCreateCartWithNullDate() {
        Cart invalidCart = CartFactory.createCart(1L, null);
        assertNull(invalidCart, "Factory should return null for null createdDate");
        System.out.println("Validation check passed: Invalid cart (null date) is null.");
    }

    @Test
    void testIdentity() {
        assertInstanceOf(Cart.class, cart);
    }
}
