
package za.ac.cput.service;

import za.ac.cput.domain.Product;
import java.util.List;
//
public interface IProductService extends Service<Product, Long> {
    List<Product> getAll();
}