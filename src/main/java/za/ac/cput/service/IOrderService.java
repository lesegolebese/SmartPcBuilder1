package za.ac.cput.service;

import za.ac.cput.domain.Order;
import java.util.List;

public interface IOrderService extends Service<Order, Long> {
    List<Order> getAll();
}
