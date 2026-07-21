package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.AdminFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/admin";
    private static Admin admin;

    @BeforeAll
    static void init() {
       admin = AdminFactory.createAdmin(
                "ADM001",
                "John",
                "Middle",
                "Doe",
                "john.doe@example.com",
                "password123"
        );
        assertNotNull(admin);
    }

    @Test
    @Order(1)
    void testCreate() {
        String url = BASE_URL + "/create";
        ResponseEntity<Admin> response = restTemplate.postForEntity(url, admin, Admin.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(admin.getAdminId(), response.getBody().getAdminId());
        System.out.println("Created Admin: " + response.getBody());
    }

    @Test
    @Order(2)
    void testRead() {
        String url = BASE_URL + "/read/" + admin.getAdminId();
        ResponseEntity<Admin> response = restTemplate.getForEntity(url, Admin.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Read Admin: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {
        String url = BASE_URL + "/update";
        Admin updatedAdmin = new Admin.Builder().copy(admin).setFirstName("JohnUpdated").build();
        HttpEntity<Admin> entity = new HttpEntity<>(updatedAdmin);
        ResponseEntity<Admin> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Admin.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("JohnUpdated", response.getBody().getFirstName());
        System.out.println("Updated Admin: " + response.getBody());
    }

    @Test
    @Order(4)
    void testGetAll() {
        String url = BASE_URL + "/getall";
        ResponseEntity<Admin[]> response = restTemplate.getForEntity(url, Admin[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All Admins count: " + response.getBody().length);
    }

    @Test
    @Order(5)
    void testFindByEmail() {
        String url = BASE_URL + "/email/" + admin.getEmail();
        ResponseEntity<Admin> response = restTemplate.getForEntity(url, Admin.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Found by Email: " + response.getBody());
    }

    @Test
    @Order(6)
    void testLogin() {
        String url = BASE_URL + "/login?email=" + admin.getEmail() + "&password=" + admin.getPassword();
        ResponseEntity<Admin> response = restTemplate.postForEntity(url, null, Admin.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Login Admin: " + response.getBody());
    }

    @Test
    @Order(7)
    void testDelete() {
        String url = BASE_URL + "/delete/" + admin.getAdminId();
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Boolean.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Boolean.TRUE.equals(response.getBody()));
        System.out.println("Deleted status: " + response.getBody());
    }
}

