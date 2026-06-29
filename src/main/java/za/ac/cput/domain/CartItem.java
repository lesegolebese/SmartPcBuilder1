/* CartItem.java
   CartItem POJO entity implementing Builder Pattern
   Author: ofentse lebaka 221108785
   Date: 22 June 2026 */

package za.ac.cput.domain;

public class CartItem {
    private String cartItemId;
    private int quantity;
    private double unitPrice;

    protected CartItem() {}

    protected CartItem(Builder builder) {
        this.cartItemId = builder.cartItemId;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
    }

    public String getCartItemId() { return cartItemId; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId='" + cartItemId + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public static class Builder {
        private String cartItemId;
        private int quantity;
        private double unitPrice;

        public Builder setCartItemId(String cartItemId) {
            this.cartItemId = cartItemId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder copy(CartItem cartItem) {
            this.cartItemId = cartItem.cartItemId;
            this.quantity = cartItem.quantity;
            this.unitPrice = cartItem.unitPrice;
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }
    }
}