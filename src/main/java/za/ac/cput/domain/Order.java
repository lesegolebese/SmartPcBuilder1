package za.ac.cput.domain;

import java.time.LocalDate;

/* Order.java
   Order POJO class

   Author: Coben Maistry

   Date: 21 June 2026
*/

public class Order {

    private Long orderId;
    private LocalDate orderDate;
    private String status;

    protected Order() {}

    protected Order(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.status = builder.status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder {
        private Long orderId;
        private LocalDate orderDate;
        private String status;

        public Builder setOrderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.orderDate = order.orderDate;
            this.status = order.status;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
