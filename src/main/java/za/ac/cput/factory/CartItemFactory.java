package za.ac.cput.factory;

import za.ac.cput.domain.CartItem;

public class CartItemFactory {

    public static CartItem createCartItem(Long cartItemId, int quantity, double unitPrice) {
        if (quantity <= 0 || unitPrice <= 0) {
            return null;
        }

        return new CartItem.Builder()
                .setCartItemId(cartItemId)
                .setQuantity(quantity)
                .setUnitPrice(unitPrice)
                .build();
    }
}