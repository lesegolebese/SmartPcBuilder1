/*
 * OrderFactoryTest.java
 * Author: Coben Maistry
 * Date: 28 June 2026
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Order;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = OrderFactory.createOrder(1L, LocalDate.now(), "Pending");
    }

    @Test
    void testCreateOrder() {
        assertNotNull(order);
        assertEquals(1L, order.getOrderId());
        System.out.println(order);
    }

    @Test
    void testCreateOrderWithInvalidData() {
        Order invalidOrder = OrderFactory.createOrder(null, null, "");
        assertNull(invalidOrder);
    }

    @Test
    void testIdentity() {
        assertInstanceOf(Order.class, order);
    }
}
