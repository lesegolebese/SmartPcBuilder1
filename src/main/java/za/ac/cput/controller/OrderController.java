package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.catalog.Product;
import za.ac.cput.domain.ordering.*;
import za.ac.cput.domain.user.User;
import za.ac.cput.repository.*;
import za.ac.cput.service.IOrderService;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping({"/api/order", "/order"})
public class OrderController {

    private static final Set<String> ALLOWED_STATUSES =
            Set.of("PENDING", "PROCESSING", "SHIPPED", "DELIVERED", "CANCELLED");

    private final IOrderService orderService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;
    private final ShippingDetailsRepository shippingDetailsRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public OrderController(IOrderService orderService, UserRepository userRepository,
                           ProductRepository productRepository, CouponRepository couponRepository,
                           ShippingDetailsRepository shippingDetailsRepository,
                           PaymentRepository paymentRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.couponRepository = couponRepository;
        this.shippingDetailsRepository = shippingDetailsRepository;
        this.paymentRepository = paymentRepository;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> create(@RequestBody Map<String, Object> orderData) {
        try {
            String userId = (String) orderData.get("userId");
            String status = (String) orderData.getOrDefault("status", "PENDING");
            List<Map<String, Object>> itemsData = castList(orderData.get("orderItems"));

            if (userId == null || userId.isBlank()) {
                return ResponseEntity.badRequest().body("User is required");
            }
            if (itemsData == null || itemsData.isEmpty()) {
                return ResponseEntity.badRequest().body("Cart cannot be empty");
            }
            if (!ALLOWED_STATUSES.contains(status.toUpperCase())) {
                return ResponseEntity.badRequest().body("Invalid order status");
            }

            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                user = userRepository.findByEmail(userId).orElse(null);
            }
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            // Resolve items, validate stock and compute authoritative totals server-side.
            List<ResolvedItem> resolved = new ArrayList<>();
            double subtotal = 0;
            for (Map<String, Object> itemData : itemsData) {
                Object productIdObj = itemData.get("productId");
                if (productIdObj == null) {
                    continue;
                }
                Product product = productRepository.findById(Long.valueOf(productIdObj.toString())).orElse(null);
                if (product == null) {
                    return ResponseEntity.badRequest().body("Product not found: " + productIdObj);
                }
                int quantity = itemData.get("quantity") == null ? 1 : Integer.parseInt(itemData.get("quantity").toString());
                if (quantity <= 0) {
                    continue;
                }
                if (product.getStockQuantity() < quantity) {
                    return ResponseEntity.badRequest().body(
                            "Insufficient stock for " + product.getProductName()
                                    + " (available: " + product.getStockQuantity() + ")");
                }
                double unitPrice = product.getEffectivePrice() != null ? product.getEffectivePrice() : product.getPrice();
                resolved.add(new ResolvedItem(product, quantity, unitPrice));
                subtotal += unitPrice * quantity;
            }

            if (resolved.isEmpty()) {
                return ResponseEntity.badRequest().body("No valid order items supplied");
            }

            // Coupon / discount
            double discount = 0;
            String couponCode = orderData.get("couponCode") == null ? null : orderData.get("couponCode").toString().trim();
            if (couponCode != null && !couponCode.isBlank()) {
                Coupon coupon = couponRepository.findById(couponCode.toUpperCase()).orElse(null);
                if (coupon == null || !coupon.isActive()) {
                    return ResponseEntity.badRequest().body("Invalid or expired coupon code");
                }
                discount = coupon.discountFor(subtotal);
                if (discount <= 0) {
                    return ResponseEntity.badRequest().body("Coupon does not apply to this order total");
                }
            }
            double total = Math.max(0, subtotal - discount);

            Order order = new Order.Builder()
                    .setOrderDate(LocalDate.now())
                    .setStatus(status.toUpperCase())
                    .setTotalAmount(Math.round(total * 100.0) / 100.0)
                    .setDiscountAmount(Math.round(discount * 100.0) / 100.0)
                    .setCouponCode(couponCode != null && !couponCode.isBlank() ? couponCode.toUpperCase() : null)
                    .setUser(user)
                    .build();

            Order savedOrder = orderService.create(order);

            List<OrderItem> orderItems = new ArrayList<>();
            for (ResolvedItem item : resolved) {
                OrderItem orderItem = new OrderItem.Builder()
                        .setOrderItemId("OI" + UUID.randomUUID())
                        .setOrder(savedOrder)
                        .setProduct(item.product)
                        .setQuantity(item.quantity)
                        .setUnitPrice(item.unitPrice)
                        .build();
                orderItems.add(orderItem);
                item.product.reduceStock(item.quantity);
                productRepository.save(item.product);
            }
            savedOrder.getOrderItems().addAll(orderItems);
            savedOrder = orderService.update(savedOrder);

            // Optional shipping details snapshot
            Map<String, Object> shippingData = asMap(orderData.get("shipping"));
            if (shippingData != null) {
                ShippingDetails shipping = new ShippingDetails.Builder()
                        .setRecipientName(str(shippingData.get("recipientName")))
                        .setStreetAddress(str(shippingData.get("streetAddress")))
                        .setCity(str(shippingData.get("city")))
                        .setProvince(str(shippingData.get("province")))
                        .setPostalCode(str(shippingData.get("postalCode")))
                        .setCountry(str(shippingData.getOrDefault("country", "South Africa")))
                        .setShippingMethod(str(shippingData.getOrDefault("shippingMethod", "STANDARD")))
                        .setShippingStatus("PENDING")
                        .setOrder(savedOrder)
                        .build();
                shippingDetailsRepository.save(shipping);
            }

            // Mock payment record
            Map<String, Object> paymentData = asMap(orderData.get("payment"));
            Payment payment = new Payment.Builder()
                    .setPaymentMethod(paymentData != null && paymentData.get("method") != null
                            ? str(paymentData.get("method")) : "CREDIT_CARD")
                    .setAmount(total)
                    .setPaymentStatus("COMPLETED")
                    .setPaymentDate(LocalDate.now())
                    .setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 12).toUpperCase())
                    .setOrder(savedOrder)
                    .build();
            paymentRepository.save(payment);

            Map<String, Object> response = new HashMap<>();
            response.put("orderId", savedOrder.getOrderId());
            response.put("totalAmount", total);
            response.put("discountAmount", discount);
            response.put("transactionId", payment.getTransactionId());
            response.put("message", "Order created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Order could not be placed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/read/{id}")
    public ResponseEntity<Order> read(@PathVariable Long id) {
        Order order = orderService.read(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Order> update(@RequestBody Order order) {
        Order updated = orderService.update(order);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
        if (!ALLOWED_STATUSES.contains(status.toUpperCase())) {
            return ResponseEntity.badRequest().body("Invalid order status. Allowed: " + ALLOWED_STATUSES);
        }
        Order order = orderService.read(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order updated = orderService.update(new Order.Builder().copy(order)
                .setStatus(status.toUpperCase()).build());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean deleted = orderService.delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getall")
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orders = orderService.getAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable String userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        if (orders == null || orders.isEmpty()) {
            User user = userRepository.findByEmail(userId).orElse(null);
            if (user != null) {
                orders = orderService.getOrdersByUserId(user.getUserId());
            }
        }
        return new ResponseEntity<>(orders == null ? List.of() : orders, HttpStatus.OK);
    }

    private String str(Object value) {
        return value == null ? null : value.toString();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> castList(Object value) {
        return value instanceof List ? (List<Map<String, Object>>) value : null;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> asMap(Object value) {
        return value instanceof Map ? (Map<String, Object>) value : null;
    }

    private static class ResolvedItem {
        final Product product;
        final int quantity;
        final double unitPrice;

        ResolvedItem(Product product, int quantity, double unitPrice) {
            this.product = product;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }
    }
}
