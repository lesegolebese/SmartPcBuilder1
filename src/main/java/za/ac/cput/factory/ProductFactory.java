package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;

public class ProductFactory {

    public static Product createProduct(Long productId,
                                        String productName,
                                        String description,
                                        Double price,
                                        int stockQuantity) {

        if (productId == null ||
                Helper.isNullOrEmpty(productName) ||
                Helper.isNullOrEmpty(description) ||
                price == null) {
            return null;
        }

        if (stockQuantity < 0) {
            return null;
        }

        return new Product.Builder()
                .setProductId(productId)
                .setProductName(productName)
                .setDescription(description)
                .setPrice(price)
                .setStockQuantity(stockQuantity)
                .build();
    }
}