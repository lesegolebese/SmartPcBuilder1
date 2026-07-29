package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.CartItem;
import static org.junit.jupiter.api.Assertions.*;

class CartItemFactoryTest {
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        cartItem = CartItemFactory.createCartItem(1L, 2, 699.99);
    }

    @Test
    void testCreateCartItem() {
        assertNotNull(cartItem);
        assertEquals(1L, cartItem.getCartItemId());
        assertEquals(2, cartItem.getQuantity());
        assertEquals(699.99, cartItem.getUnitPrice());
        System.out.println(cartItem.toString());
    }

    @Test
    void testCreateCartItemWithInvalidQuantity() {
        CartItem invalidItem = CartItemFactory.createCartItem(2L, 0, 699.99);
        assertNull(invalidItem, "Factory should return null for invalid quantity");
        System.out.println("Validation check passed: Invalid cart item is null.");
    }

    @Test
    void testCreateCartItemWithNullCartItemId() {
        CartItem invalidItem = CartItemFactory.createCartItem(null, 1, 699.99);
        assertNotNull(invalidItem, "Factory should allow null cartItemId for generated IDs");
        System.out.println("Validation check passed: Cart item with null id is allowed for generated IDs.");
    }

    @Test
    void testCreateCartItemWithInvalidPrice() {
        CartItem invalidItem = CartItemFactory.createCartItem(4L, 1, -100);
        assertNull(invalidItem, "Factory should return null for negative price");
        System.out.println("Validation check passed: Invalid cart item (negative price) is null.");
    }

    @Test
    void testIdentity() {
        assertInstanceOf(CartItem.class, cartItem);
    }
}
