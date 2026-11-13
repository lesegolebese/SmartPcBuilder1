package za.ac.cput.service;

import za.ac.cput.domain.ordering.Order;
import java.util.List;

public interface IOrderService extends IService<Order, Long> {
    List<Order> getAll();
    List<Order> getOrdersByUserId(String userId);
}
