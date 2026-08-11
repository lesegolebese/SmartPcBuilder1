package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.Main;
import za.ac.cput.domain.CartItem;
import za.ac.cput.factory.CartItemFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartItemServiceTest {

    @Autowired
    private CartItemService service;

    private static CartItem cartItem;

    @BeforeAll
    static void init() {
        cartItem = CartItemFactory.createCartItem(null, 2, 699.99);
        assertNotNull(cartItem);
        assertNull(cartItem.getCartItemId());
    }

    @Test
    @Order(1)
    void testCreate() {
        CartItem created = service.create(cartItem);
        assertNotNull(created);
        assertNotNull(created.getCartItemId());
        cartItem = created;
        System.out.println("Service Created CartItem: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        CartItem read = service.read(cartItem.getCartItemId());
        assertNotNull(read);
        assertEquals(cartItem.getCartItemId(), read.getCartItemId());
        System.out.println("Service Read CartItem: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        CartItem updatedItem = new CartItem.Builder().copy(cartItem).setQuantity(3).build();
        CartItem updated = service.update(updatedItem);
        assertNotNull(updated);
        assertEquals(3, updated.getQuantity());
        cartItem = updated;
        System.out.println("Service Updated CartItem: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<CartItem> list = service.getAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        System.out.println("Total CartItems in Service: " + list.size());
    }

    @Test
    @Order(5)
    void testDelete() {
        boolean deleted = service.delete(cartItem.getCartItemId());
        assertTrue(deleted);
        CartItem read = service.read(cartItem.getCartItemId());
        assertNull(read);
        System.out.println("CartItem successfully deleted from Service context.");
    }
}
