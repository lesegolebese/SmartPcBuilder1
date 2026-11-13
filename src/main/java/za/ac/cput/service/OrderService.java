package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ordering.Order;
import za.ac.cput.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order create(Order order) {
        if (order == null) {
            return null;
        }
        return repository.save(order);
    }

    @Override
    public Order read(Long id) {
        if (id == null) {
            return null;
        }
        return repository.findById(id).orElse(null);
    }

    @Override
    public Order update(Order order) {
        if (order == null || order.getOrderId() == null) {
            return null;
        }
        if (repository.existsById(order.getOrderId())) {
            return repository.save(order);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return !repository.existsById(id);
        }
        return false;
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        List<Order> byUserId = repository.findByUser_UserId(userId);
        if (byUserId != null && !byUserId.isEmpty()) {
            return byUserId;
        }
        return repository.findByUser_Email(userId);
    }
}
