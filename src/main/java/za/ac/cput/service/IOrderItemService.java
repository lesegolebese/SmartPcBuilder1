package za.ac.cput.service;

import za.ac.cput.domain.OrderItem;
import java.util.List;

public interface IOrderItemService extends IService<OrderItem, String> {
    List<OrderItem> getAll();
}
