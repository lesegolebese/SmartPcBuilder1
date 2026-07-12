package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Order;
import za.ac.cput.factory.OrderFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest {

    @Autowired
    private OrderService service;

    private static Order order;

    @BeforeAll
    static void init() {
        order = OrderFactory.createOrder(
                1001L,
                LocalDate.now(),
                "Pending"
        );
        assertNotNull(order);
    }

    @Test
    @Order(1)
    void testCreate() {
        Order created = service.create(order);
        assertNotNull(created);
        System.out.println("Service Created Order: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        Order read = service.read(order.getOrderId());
        assertNotNull(read);
        System.out.println("Service Read Order: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Order updatedOrder = new Order.Builder()
                .copy(order)
                .setStatus("Completed")
                .build();

        Order updated = service.update(updatedOrder);

        assertNotNull(updated);
        assertEquals("Completed", updated.getStatus());

        System.out.println("Service Updated Order: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<Order> list = service.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);

        System.out.println("Total Orders in Service: " + list.size());
    }

    @Test
    @Order(5)
    void testDelete() {
        boolean deleted = service.delete(order.getOrderId());

        assertTrue(deleted);

        Order read = service.read(order.getOrderId());

        assertNull(read);

        System.out.println("Order successfully deleted from Service context.");
    }
}
