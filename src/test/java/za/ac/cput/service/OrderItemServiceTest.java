package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.factory.OrderItemFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderItemServiceTest {

    @Autowired
    private OrderItemService service;

    private static OrderItem orderItem;

    @BeforeAll
    static void init() {
        orderItem = OrderItemFactory.createOrderItem(
                "OI1001",
                2,
                1499.99
        );

        assertNotNull(orderItem);
    }

    @Test
    @Order(1)
    void testCreate() {
        OrderItem created = service.create(orderItem);

        assertNotNull(created);

        System.out.println("Service Created OrderItem: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        OrderItem read = service.read(orderItem.getOrderItemId());

        assertNotNull(read);

        System.out.println("Service Read OrderItem: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        OrderItem updatedOrderItem = new OrderItem.Builder()
                .copy(orderItem)
                .setQuantity(5)
                .build();

        OrderItem updated = service.update(updatedOrderItem);

        assertNotNull(updated);
        assertEquals(5, updated.getQuantity());

        System.out.println("Service Updated OrderItem: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<OrderItem> list = service.getAll();

        assertNotNull(list);
        assertTrue(list.size() > 0);

        System.out.println("Total OrderItems in Service: " + list.size());
    }

    @Test
    @Order(5)
    void testDelete() {
        boolean deleted = service.delete(orderItem.getOrderItemId());

        assertTrue(deleted);

        OrderItem read = service.read(orderItem.getOrderItemId());

        assertNull(read);

        System.out.println("OrderItem successfully deleted from Service context.");
    }
}
