package za.ac.cput.factory;

import za.ac.cput.domain.CartItem;
import za.ac.cput.util.Helper;

public class CartItemFactory {

    public static CartItem createCartItem(String cartItemId, int quantity, double unitPrice) {
        if (Helper.isNullOrEmpty(cartItemId) || quantity <= 0 || unitPrice <= 0) {
            return null;
        }

        return new CartItem.Builder()
                .setCartItemId(cartItemId)
                .setQuantity(quantity)
                .setUnitPrice(unitPrice)
                .build();
    }
}