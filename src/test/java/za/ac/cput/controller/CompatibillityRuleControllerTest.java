package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.CompatibilityRule;
import za.ac.cput.factory.CompatibillityRuleFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompatibillityRuleControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/compatibilityrule";
    private static CompatibilityRule rule;

    @BeforeAll
    static void init() {
        rule = CompatibillityRuleFactory.createCompatibilityRule(
                "RULE001",
                "GPU",
                "RTX 4090 compatibility",
                "Compatible with 850W+ PSU"
        );
        assertNotNull(rule);
    }

    @Test
    @Order(1)
    void testCreate() {
        String url = BASE_URL + "/create";
        ResponseEntity<CompatibilityRule> response = restTemplate.postForEntity(url, rule, CompatibilityRule.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Created Rule: " + response.getBody());
    }

    @Test
    @Order(2)
    void testRead() {
        String url = BASE_URL + "/read/" + rule.getRuleId();
        ResponseEntity<CompatibilityRule> response = restTemplate.getForEntity(url, CompatibilityRule.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Read Rule: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {
        String url = BASE_URL + "/update";
        CompatibilityRule updated = new CompatibilityRule.Builder().copy(rule).setDescription("Updated description").build();
        HttpEntity<CompatibilityRule> entity = new HttpEntity<>(updated);
        ResponseEntity<CompatibilityRule> response = restTemplate.exchange(url, HttpMethod.PUT, entity, CompatibilityRule.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated description", response.getBody().getDescription());
        System.out.println("Updated Rule: " + response.getBody());
    }

    @Test
    @Order(4)
    void testGetAll() {
        String url = BASE_URL + "/getall";
        ResponseEntity<CompatibilityRule[]> response = restTemplate.getForEntity(url, CompatibilityRule[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All Rules count: " + response.getBody().length);
    }

    @Test
    @Order(5)
    void testFindByComponentType() {
        String url = BASE_URL + "/componenttype/" + rule.getComponentType1();
        ResponseEntity<CompatibilityRule[]> response = restTemplate.getForEntity(url, CompatibilityRule[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Found by component type count: " + response.getBody().length);
    }

    @Test
    @Order(6)
    void testFindByDescription() {
        String url = BASE_URL + "/description/" + rule.getDescription();
        ResponseEntity<CompatibilityRule[]> response = restTemplate.getForEntity(url, CompatibilityRule[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Found by description count: " + response.getBody().length);
    }

    @Test
    @Order(7)
    void testSearchByKeyword() {
        String keyword = "850W";
        String url = BASE_URL + "/search/" + keyword;
        ResponseEntity<CompatibilityRule[]> response = restTemplate.getForEntity(url, CompatibilityRule[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Search results count: " + response.getBody().length);
    }

    @Test
    @Order(8)
    void testDelete() {
        String url = BASE_URL + "/delete/" + rule.getRuleId();
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Boolean.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Boolean.TRUE.equals(response.getBody()));
        System.out.println("Deleted status: " + response.getBody());
    }
}

