/* Cart.java
   Cart POJO entity implementing Builder Pattern
   Author: Ofentse lebaka
   Date: 22 June 2026 */

package za.ac.cput.domain;

import java.time.LocalDate;

public class Cart {
    private Long cartId;
    private LocalDate createdDate;

    protected Cart() {}

    protected Cart(Builder builder) {
        this.cartId = builder.cartId;
        this.createdDate = builder.createdDate;
    }

    public Long getCartId() { return cartId; }
    public LocalDate getCreatedDate() { return createdDate; }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", createdDate=" + createdDate +
                '}';
    }

    public static class Builder {
        private Long cartId;
        private LocalDate createdDate;

        public Builder setCartId(Long cartId) {
            this.cartId = cartId;
            return this;
        }

        public Builder setCreatedDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder copy(Cart cart) {
            this.cartId = cart.cartId;
            this.createdDate = cart.createdDate;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}
