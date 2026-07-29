package za.ac.cput.factory;

import za.ac.cput.domain.Cart;

import java.time.LocalDate;

public class CartFactory {

    public static Cart createCart(Long cartId, LocalDate createdDate) {
        if (createdDate == null) {
            return null;
        }

        return new Cart.Builder()
                .setCartId(cartId)
                .setCreatedDate(createdDate)
                .build();
    }
}
