package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.PcBuildItem;
import za.ac.cput.factory.PcBuildItemFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PcBuildItemControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/pcbuilditem";
    private static PcBuildItem pcBuildItem;

    @BeforeAll
    static void init() {
        pcBuildItem = PcBuildItemFactory.createPcBuildItem(
                "ITM-101",
                "NVIDIA RTX 4070 Ti",
                1,
                16500.00
        );
        assertNotNull(pcBuildItem);
    }

    @Test
    @Order(1)
    void testCreate() {
        String url = BASE_URL + "/create";
        ResponseEntity<PcBuildItem> response = restTemplate.postForEntity(url, pcBuildItem, PcBuildItem.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Created PcBuildItem: " + response.getBody());
    }

    @Test
    @Order(2)
    void testRead() {
        String url = BASE_URL + "/read/" + pcBuildItem.getBuildItemId();
        ResponseEntity<PcBuildItem> response = restTemplate.getForEntity(url, PcBuildItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Read PcBuildItem: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {
        String url = BASE_URL + "/update";
        PcBuildItem updatedPcBuildItem = new PcBuildItem.Builder().copy(pcBuildItem).setQuantity(2).build();
        HttpEntity<PcBuildItem> entity = new HttpEntity<>(updatedPcBuildItem);
        ResponseEntity<PcBuildItem> response = restTemplate.exchange(url, HttpMethod.PUT, entity, PcBuildItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getQuantity());
        System.out.println("Updated PcBuildItem: " + response.getBody());
    }

    @Test
    @Order(4)
    void testGetAll() {
        String url = BASE_URL + "/getall";
        ResponseEntity<PcBuildItem[]> response = restTemplate.getForEntity(url, PcBuildItem[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("All PcBuildItems count: " + response.getBody().length);
    }

    @Test
    @Order(5)
    void testDelete() {
        String url = BASE_URL + "/delete/" + pcBuildItem.getBuildItemId();
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Boolean.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Boolean.TRUE.equals(response.getBody()));
        System.out.println("Deleted status: " + response.getBody());
    }
}