package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.factory.OrderItemFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderItemControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/orderItem";
    private static OrderItem orderItem;

    @BeforeAll
    static void init() {

        orderItem = OrderItemFactory.createOrderItem(
                "OI001",
                2,
                399.99
        );

        assertNotNull(orderItem);
    }

    @Test
    @Order(1)
    void testCreate() {

        String url = BASE_URL + "/create";

        ResponseEntity<OrderItem> response =
                restTemplate.postForEntity(url,
                        orderItem,
                        OrderItem.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("Created Order Item: " + response.getBody());
    }

    @Test
    @Order(2)
    void testRead() {

        String url = BASE_URL + "/read/" + orderItem.getOrderItemId();

        ResponseEntity<OrderItem> response =
                restTemplate.getForEntity(url,
                        OrderItem.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("Read Order Item: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {

        String url = BASE_URL + "/update";

        OrderItem updated = new OrderItem.Builder()
                .copy(orderItem)
                .setQuantity(5)
                .build();

        HttpEntity<OrderItem> entity = new HttpEntity<>(updated);

        ResponseEntity<OrderItem> response =
                restTemplate.exchange(url,
                        HttpMethod.PUT,
                        entity,
                        OrderItem.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().getQuantity());

        System.out.println("Updated Order Item: " + response.getBody());
    }

    @Test
    @Order(4)
    void testGetAll() {

        String url = BASE_URL + "/getall";

        ResponseEntity<OrderItem[]> response =
                restTemplate.getForEntity(url,
                        OrderItem[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("All Order Items: " + response.getBody().length);
    }

    @Test
    @Order(5)
    void testDelete() {

        String url = BASE_URL + "/delete/" + orderItem.getOrderItemId();

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
