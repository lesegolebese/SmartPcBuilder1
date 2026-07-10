package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CartFactoryTest {
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = CartFactory.createCart(1L, LocalDate.now());
    }

    @Test
    void testCreateCart() {
        assertNotNull(cart);
        assertEquals(1L, cart.getCartId());
        assertEquals(LocalDate.now(), cart.getCreatedDate());
        System.out.println(cart.toString());
    }

    @Test
    void testCreateCartWithNullCartId() {
        Cart invalidCart = CartFactory.createCart(null, LocalDate.now());
        assertNull(invalidCart, "Factory should return null for null cartId");
        System.out.println("Validation check passed: Invalid cart (null id) is null.");
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
