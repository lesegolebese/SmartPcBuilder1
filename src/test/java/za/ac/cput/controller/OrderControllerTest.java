package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.factory.OrderFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/order";
    private static za.ac.cput.domain.Order order;

    @BeforeAll
    static void init() {
        order = OrderFactory.createOrder(
                1L,
                LocalDate.now(),
                "Pending"
        );

        assertNotNull(order);
    }

    @Test
    @Order(1)
    void testCreate() {
        String url = BASE_URL + "/create";
        ResponseEntity<za.ac.cput.domain.Order> response =
                restTemplate.postForEntity(url, order, za.ac.cput.domain.Order.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("Created Order: " + response.getBody());
    }

    @Test
    @Order(2)
    void testRead() {
        String url = BASE_URL + "/read/" + order.getOrderId();

        ResponseEntity<za.ac.cput.domain.Order> response =
                restTemplate.getForEntity(url, za.ac.cput.domain.Order.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("Read Order: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {

        String url = BASE_URL + "/update";

        za.ac.cput.domain.Order updatedOrder = new za.ac.cput.domain.Order.Builder()
                .copy(order)
                .setStatus("Completed")
                .build();

        HttpEntity<za.ac.cput.domain.Order> entity = new HttpEntity<>(updatedOrder);

        ResponseEntity<za.ac.cput.domain.Order> response =
                restTemplate.exchange(url,
                        HttpMethod.PUT,
                        entity,
                        za.ac.cput.domain.Order.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Completed", response.getBody().getStatus());

        System.out.println("Updated Order: " + response.getBody());
    }

    @Test
    @Order(4)
    void testGetAll() {

        String url = BASE_URL + "/getall";

        ResponseEntity<za.ac.cput.domain.Order[]> response =
                restTemplate.getForEntity(url, za.ac.cput.domain.Order[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("All Orders: " + response.getBody().length);
    }

    @Test
    @Order(5)
    void testDelete() {

        String url = BASE_URL + "/delete/" + order.getOrderId();

        ResponseEntity<Boolean> response =
                restTemplate.exchange(url,
                        HttpMethod.DELETE,
                        null,
                        Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Boolean.TRUE.equals(response.getBody()));

        System.out.println("Deleted: " + response.getBody());
    }
}
