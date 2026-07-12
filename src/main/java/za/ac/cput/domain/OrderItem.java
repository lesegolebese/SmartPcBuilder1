package za.ac.cput.domain;

import jakarta.persistence.*;

/* OrderItem.java
   OrderItem POJO class

   Author: Coben Maistry

   Date: 21 June 2026
*/

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    private String orderItemId;

    private int quantity;
    private double unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    protected OrderItem() {}

    protected OrderItem(Builder builder) {
        this.orderItemId = builder.orderItemId;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.order = builder.order;
        this.product = builder.product;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

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
        private Order order;
        private Product product;

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

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder copy(OrderItem orderItem) {
            this.orderItemId = orderItem.orderItemId;
            this.quantity = orderItem.quantity;
            this.unitPrice = orderItem.unitPrice;
            this.order = orderItem.order;
            this.product = orderItem.product;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
