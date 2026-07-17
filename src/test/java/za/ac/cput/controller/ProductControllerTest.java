package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.ProductFactory;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/product";
    private static Product product;

    @BeforeAll
    static void init() {
        product = ProductFactory.createProduct(
                1L,
                "Gaming Laptop",
                "RTX 4060 Gaming Laptop",
                25000.00,
                10
        );
        assertNotNull(product);
    }

    @Test
    @Order(1)
    void testCreate() {
        String url = BASE_URL + "/create";
        ResponseEntity<Product> response = restTemplate.postForEntity(url, product, Product.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        product = response.getBody();
        System.out.println(product);
    }

    @Test
    @Order(2)
    void testRead() {
        String url = BASE_URL + "/read/" + product.getProductId();

        ResponseEntity<Product> response =
                restTemplate.getForEntity(url, Product.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {

        String url = BASE_URL + "/update";

        Product updated =
                new Product.Builder()
                        .copy(product)
                        .setPrice(27000.00)
                        .build();

        HttpEntity<Product> entity = new HttpEntity<>(updated);

        ResponseEntity<Product> response =
                restTemplate.exchange(url, HttpMethod.PUT, entity, Product.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(4)
    void testGetAll() {

        String url = BASE_URL + "/getall";

        ResponseEntity<Product[]> response =
                restTemplate.getForEntity(url, Product[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(5)
    void testDelete() {

        String url = BASE_URL + "/delete/" + product.getProductId();

        ResponseEntity<Boolean> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Boolean.TRUE.equals(response.getBody()));
    }
}