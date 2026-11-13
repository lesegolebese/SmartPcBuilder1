package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import za.ac.cput.domain.user.User;

/* Order.java
   Order POJO class
   Author: Coben Maistry
   Date: 21 June 2026
*/

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate orderDate;
    private String status;
    private Double totalAmount;
    private Double discountAmount;
    private String couponCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Payment payment;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private ShippingDetails shippingDetails;

    protected Order() {}

    protected Order(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.status = builder.status;
        this.totalAmount = builder.totalAmount;
        this.discountAmount = builder.discountAmount;
        this.couponCode = builder.couponCode;
        this.user = builder.user;
        this.orderItems = builder.orderItems;
        this.payment = builder.payment;
        this.shippingDetails = builder.shippingDetails;
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Payment getPayment() {
        return payment;
    }

    public ShippingDetails getShippingDetails() {
        return shippingDetails;
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
        private Double totalAmount;
        private Double discountAmount;
        private String couponCode;
        private User user;
        private List<OrderItem> orderItems = new ArrayList<>();
        private Payment payment;
        private ShippingDetails shippingDetails;

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

        public Builder setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setDiscountAmount(Double discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        public Builder setCouponCode(String couponCode) {
            this.couponCode = couponCode;
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

        public Builder setPayment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public Builder setShippingDetails(ShippingDetails shippingDetails) {
            this.shippingDetails = shippingDetails;
            return this;
        }

        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.orderDate = order.orderDate;
            this.status = order.status;
            this.totalAmount = order.totalAmount;
            this.discountAmount = order.discountAmount;
            this.couponCode = order.couponCode;
            this.user = order.user;
            this.orderItems = order.orderItems;
            this.payment = order.payment;
            this.shippingDetails = order.shippingDetails;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
