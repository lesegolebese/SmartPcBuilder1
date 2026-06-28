package za.ac.cput.domain;

/* OrderItem.java
   OrderItem POJO class

   Author: Coben Maistry

   Date: 21 June 2026
*/

public class OrderItem {
    private String orderItemId;
    private int quantity;
    private double unitPrice;

    protected OrderItem() {}

    protected OrderItem(Builder builder) {
        this.orderItemId = builder.orderItemId;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
    }

    public String getOrderItemId() { return orderItemId; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId='" + orderItemId + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public static class Builder {
        private String orderItemId;
        private int quantity;
        private double unitPrice;

        public Builder setOrderItemId(String orderItemId) {
            this.orderItemId = orderItemId;
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

        public Builder copy(OrderItem orderItem) {
            this.orderItemId = orderItem.orderItemId;
            this.quantity = orderItem.quantity;
            this.unitPrice = orderItem.unitPrice;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}