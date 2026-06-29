package za.ac.cput.factory;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = ProductFactory.createProduct(
                1L,
                "Laptop",
                "Gaming Laptop",
                15000.00,
                10
        );
    }

    @Test
    void testCreateProduct() {
        assertNotNull(product);
        assertEquals(1L, product.getProductId());
        assertEquals("Laptop", product.getProductName());
        System.out.println(product);
    }

    @Test
    void testCreateProductWithMissingData() {
        Product invalid = ProductFactory.createProduct(
                null,
                "",
                "",
                null,
                -1
        );

        assertNull(invalid);
    }

    @Test
    void testIdentity() {
        assertInstanceOf(Product.class, product);
    }
}