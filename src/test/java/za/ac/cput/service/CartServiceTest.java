package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.Main;
import za.ac.cput.domain.Cart;
import za.ac.cput.factory.CartFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartServiceTest {

    @Autowired
    private CartService service;

    private static Cart cart;
    private static LocalDate createdDate;

    @BeforeAll
    static void init() {
        createdDate = LocalDate.now();
        cart = CartFactory.createCart(null, createdDate);
        assertNotNull(cart);
        assertNull(cart.getCartId());
    }

    @Test
    @Order(1)
    void testCreate() {
        Cart created = service.create(cart);
        assertNotNull(created);
        assertNotNull(created.getCartId());
        cart = created;
        System.out.println("Service Created Cart: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        Cart read = service.read(cart.getCartId());
        assertNotNull(read);
        assertEquals(cart.getCartId(), read.getCartId());
        System.out.println("Service Read Cart: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Cart updatedCart = new Cart.Builder().copy(cart).setCreatedDate(createdDate.plusDays(1)).build();
        Cart updated = service.update(updatedCart);
        assertNotNull(updated);
        assertEquals(createdDate.plusDays(1), updated.getCreatedDate());
        cart = updated;
        System.out.println("Service Updated Cart: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<Cart> list = service.getAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        System.out.println("Total Carts in Service: " + list.size());
    }

    @Test
    @Order(5)
    void testDelete() {
        boolean deleted = service.delete(cart.getCartId());
        assertTrue(deleted);
        Cart read = service.read(cart.getCartId());
        assertNull(read);
        System.out.println("Cart successfully deleted from Service context.");
    }
}
