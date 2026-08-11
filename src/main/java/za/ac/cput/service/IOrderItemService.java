package za.ac.cput.service;

import za.ac.cput.domain.OrderItem;
import java.util.List;

public interface IOrderItemService extends IService<OrderItem, Long> {
    List<OrderItem> getAll();
}
