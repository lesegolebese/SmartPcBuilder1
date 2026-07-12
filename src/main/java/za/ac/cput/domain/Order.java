package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/* Order.java
   Order POJO class

   Author: Coben Maistry

   Date: 21 June 2026
*/

@Entity
@Table(name = "orders")
   public class Order {
@Id
      
private Long orderId;
private LocalDate orderDate;
private String status;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")
    private User user;

@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
private List<OrderItem> orderItems = new ArrayList<>();

protected Order() {}
protected Order(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.status = builder.status;
        this.user = builder.user;
        this.orderItems = builder.orderItems;
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

    public User getUser() {
        return user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
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
        private User user;
        private List<OrderItem> orderItems = new ArrayList<>();

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

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.orderDate = order.orderDate;
            this.status = order.status;
            this.user = order.user;
            this.orderItems = order.orderItems;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
