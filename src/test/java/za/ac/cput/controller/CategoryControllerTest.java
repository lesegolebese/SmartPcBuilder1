package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Category;
import za.ac.cput.factory.CategoryFactory;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/category";
    private static Category category;

    @BeforeAll
    static void init() {
        category = CategoryFactory.createCategory(
                1L,
                "Laptop",
                "Gaming Laptops"
        );
        assertNotNull(category);
    }

    @Test
    @Order(1)
    void testCreate() {
        String url = BASE_URL + "/create";
        ResponseEntity<Category> response =
                restTemplate.postForEntity(url, category, Category.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        category = response.getBody();
        System.out.println("Created Category: " + response.getBody());
    }

    @Test
    @Order(2)
    void testRead() {
        String url = BASE_URL + "/read/" + category.getCategoryId();

        ResponseEntity<Category> response =
                restTemplate.getForEntity(url, Category.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("Read Category: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {
        String url = BASE_URL + "/update";

        Category updatedCategory =
                new Category.Builder()
                        .copy(category)
                        .setDescription("Business and Gaming Laptops")
                        .build();

        HttpEntity<Category> entity = new HttpEntity<>(updatedCategory);

        ResponseEntity<Category> response =
                restTemplate.exchange(url, HttpMethod.PUT, entity, Category.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("Updated Category: " + response.getBody());
    }

    @Test
    @Order(4)
    void testGetAll() {
        String url = BASE_URL + "/getall";

        ResponseEntity<Category[]> response =
                restTemplate.getForEntity(url, Category[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("All Categories: " + response.getBody().length);
    }

    @Test
    @Order(5)
    void testDelete() {
        String url = BASE_URL + "/delete/" + category.getCategoryId();

        ResponseEntity<Boolean> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Boolean.TRUE.equals(response.getBody()));

        System.out.println("Deleted Status: " + response.getBody());
    }
}