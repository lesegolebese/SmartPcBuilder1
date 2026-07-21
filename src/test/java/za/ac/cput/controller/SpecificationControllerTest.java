package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Specification;
import za.ac.cput.factory.SpecificationFactory;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpecificationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/specification";
    private static Specification specification;

    @BeforeAll
    static void init() {
        specification = SpecificationFactory.createSpecification(
                1L,
                "RAM",
                "16GB DDR5"
        );
        assertNotNull(specification);
    }

    @Test
    @Order(1)
    void testCreate() {
        String url = BASE_URL + "/create";

        ResponseEntity<Specification> response =
                restTemplate.postForEntity(url, specification, Specification.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        specification = response.getBody();

        System.out.println("Created Specification: " + response.getBody());
    }

    @Test
    @Order(2)
    void testRead() {
        String url = BASE_URL + "/read/" + specification.getSpecificationId();

        ResponseEntity<Specification> response =
                restTemplate.getForEntity(url, Specification.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("Read Specification: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {
        String url = BASE_URL + "/update";

        Specification updatedSpecification =
                new Specification.Builder()
                        .copy(specification)
                        .setValue("32GB DDR5")
                        .build();

        HttpEntity<Specification> entity = new HttpEntity<>(updatedSpecification);

        ResponseEntity<Specification> response =
                restTemplate.exchange(url, HttpMethod.PUT, entity, Specification.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("Updated Specification: " + response.getBody());
    }

    @Test
    @Order(4)
    void testGetAll() {
        String url = BASE_URL + "/getall";

        ResponseEntity<Specification[]> response =
                restTemplate.getForEntity(url, Specification[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("All Specifications: " + response.getBody().length);
    }

    @Test
    @Order(5)
    void testDelete() {
        String url = BASE_URL + "/delete/" + specification.getSpecificationId();

        ResponseEntity<Boolean> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Boolean.TRUE.equals(response.getBody()));

        System.out.println("Deleted Status: " + response.getBody());
    }
}