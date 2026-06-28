/*
 * OrderItemFactory.java
 * Author: Coben Maistry
 * Date: 28 June 2026
 */
package za.ac.cput.factory;

import za.ac.cput.domain.OrderItem;
import za.ac.cput.util.Helper;

public class OrderItemFactory {

    public static OrderItem createOrderItem(String orderItemId, int quantity, double unitPrice) {

        if (Helper.isNullOrEmpty(orderItemId) || quantity <= 0 || unitPrice <= 0) {
            return null;
        }

        return new OrderItem.Builder()
                .setOrderItemId(orderItemId)
                .setQuantity(quantity)
                .setUnitPrice(unitPrice)
                .build();
    }
}
