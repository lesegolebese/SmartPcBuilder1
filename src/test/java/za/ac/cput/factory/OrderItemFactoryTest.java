/*
 * OrderItemFactoryTest.java
 * Author: Coben Maistry
 * Date: 28 June 2026
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.OrderItem;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemFactoryTest {

    private OrderItem orderItem;

    @BeforeEach
    void setUp() {
        orderItem = OrderItemFactory.createOrderItem("OI001", 2, 199.99);
    }

    @Test
    void testCreateOrderItem() {
        assertNotNull(orderItem);
        assertEquals("OI001", orderItem.getOrderItemId());
        System.out.println(orderItem);
    }

    @Test
    void testCreateOrderItemWithInvalidData() {
        OrderItem invalidOrderItem = OrderItemFactory.createOrderItem("", 0, 0);
        assertNull(invalidOrderItem);
    }

    @Test
    void testIdentity() {
        assertInstanceOf(OrderItem.class, orderItem);
    }
}
