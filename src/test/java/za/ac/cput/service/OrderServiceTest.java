package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.factory.OrderFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest {

    @Autowired
    private OrderService service;

    private static za.ac.cput.domain.Order order;
    private static Long createdOrderId;

    @BeforeAll
    static void init() {
        order = OrderFactory.createOrder(
                null,
                LocalDate.now(),
                "Pending"
        );
        assertNotNull(order);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void testCreate() {
        za.ac.cput.domain.Order created = service.create(order);
        assertNotNull(created);
        createdOrderId = created.getOrderId();
        order = created;
        System.out.println("Service Created Order: " + created);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void testRead() {
        za.ac.cput.domain.Order read = service.read(createdOrderId);
        assertNotNull(read);
        System.out.println("Service Read Order: " + read);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void testUpdate() {
        za.ac.cput.domain.Order updatedOrder = new za.ac.cput.domain.Order.Builder()
                .copy(order)
                .setOrderId(createdOrderId)
                .setStatus("Completed")
                .build();

        za.ac.cput.domain.Order updated = service.update(updatedOrder);

        assertNotNull(updated);
        assertEquals("Completed", updated.getStatus());

        System.out.println("Service Updated Order: " + updated);
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void testGetAll() {
        List<za.ac.cput.domain.Order> list = service.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);

        System.out.println("Total Orders in Service: " + list.size());
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void testDelete() {
        boolean deleted = service.delete(createdOrderId);

        assertTrue(deleted);

        za.ac.cput.domain.Order read = service.read(createdOrderId);

        assertNull(read);

        System.out.println("Order successfully deleted from Service context.");
    }
}
