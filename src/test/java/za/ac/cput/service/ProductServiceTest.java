
package za.ac.cput.service;

import org.junit.jupiter.api.*;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.ProductFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceTest {

    @Autowired
    private ProductService service;

    private static Product product;

    @BeforeAll
    static void init() {
        product = ProductFactory.createProduct(
                null,
                "Gaming Laptop",
                "High performance gaming laptop",
                25999.99,
                10
        );
        assertNotNull(product);
    }

    @Test
    @Order(1)
    void testCreate() {
        product = service.create(product);
        assertNotNull(product);
        System.out.println(product);
    }

    @Test
    @Order(2)
    void testRead() {
        Product read = service.read(product.getProductId());
        assertNotNull(read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Product updatedProduct = new Product.Builder()
                .copy(product)
                .setPrice(27999.99)
                .build();

        Product updated = service.update(updatedProduct);

        assertNotNull(updated);
        assertEquals(27999.99, updated.getPrice());
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<Product> list = service.getAll();
        assertFalse(list.isEmpty());
    }

    @Test
    @Order(5)
    void testDelete() {
        assertTrue(service.delete(product.getProductId()));
    }
}