package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/user";
    private static User user;

    @BeforeAll
    static void init() {
        user = UserFactory.createUser(
                "U123",
                "John",
                "Middle",
                "Doe",
                "john.doe@example.com",
                "password123",
                "0812345678"
        );
        assertNotNull(user);
    }

    @Test
    @Order(1)
    void testCreate() {
        String url = BASE_URL + "/create";
        ResponseEntity<User> response = restTemplate.postForEntity(url, user, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user.getUserId(), response.getBody().getUserId());
        System.out.println("Created User: " + response.getBody());
    }

    @Test
    @Order(2)
    void testRead() {
        String url = BASE_URL + "/read/" + user.getUserId();
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Read User: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {
        String url = BASE_URL + "/update";
        User updatedUser = new User.Builder().copy(user).setFirstName("JohnUpdated").build();
        HttpEntity<User> entity = new HttpEntity<>(updatedUser);
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.PUT, entity, User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("JohnUpdated", response.getBody().getFirstName());
        System.out.println("Updated User: " + response.getBody());
    }

    @Test
    @Order(4)
    void testGetAll() {
        String url = BASE_URL + "/getall";
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All Users count: " + response.getBody().length);
    }

    @Test
    @Order(5)
    void testDelete() {
        String url = BASE_URL + "/delete/" + user.getUserId();
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Boolean.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Boolean.TRUE.equals(response.getBody()));
        System.out.println("Deleted status: " + response.getBody());
    }
}