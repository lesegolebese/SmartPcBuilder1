/*
 * OrderFactory.java
 * Author: Coben Maistry
 * Date: 28 June 2026
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class OrderFactory {

    public static Order createOrder(Long orderId, LocalDate orderDate, String status) {

        if (orderId == null || orderDate == null || Helper.isNullOrEmpty(status)) {
            return null;
        }

        return new Order.Builder()
                .setOrderId(orderId)
                .setOrderDate(orderDate)
                .setStatus(status)
                .build();
    }
}
