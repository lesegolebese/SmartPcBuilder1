package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class CartFactory {

    public static Cart createCart(Long cartId, LocalDate createdDate) {
        if (cartId == null || createdDate == null) {
            return null;
        }

        return new Cart.Builder()
                .setCartId(cartId)
                .setCreatedDate(createdDate)
                .build();
    }
}
