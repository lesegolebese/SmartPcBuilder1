package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.PcBuild;
import za.ac.cput.factory.PcBuildFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PcBuildControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/pcbuild";
    private static PcBuild pcBuild;

    @BeforeAll
    static void init() {
        pcBuild = PcBuildFactory.createPcBuild(
                "BLD-001",
                "Custom Gaming Rig",
                25000.00,
                "High performance gaming build with liquid cooling."
        );
        assertNotNull(pcBuild);
    }

    @Test
    @Order(1)
    void testCreate() {
        String url = BASE_URL + "/create";
        ResponseEntity<PcBuild> response = restTemplate.postForEntity(url, pcBuild, PcBuild.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Created PcBuild: " + response.getBody());
    }

    @Test
    @Order(2)
    void testRead() {
        String url = BASE_URL + "/read/" + pcBuild.getBuildId();
        ResponseEntity<PcBuild> response = restTemplate.getForEntity(url, PcBuild.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Read PcBuild: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {
        String url = BASE_URL + "/update";
        PcBuild updatedPcBuild = new PcBuild.Builder().copy(pcBuild).setBuildName("Ultimate Workstation").build();
        HttpEntity<PcBuild> entity = new HttpEntity<>(updatedPcBuild);
        ResponseEntity<PcBuild> response = restTemplate.exchange(url, HttpMethod.PUT, entity, PcBuild.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ultimate Workstation", response.getBody().getBuildName());
        System.out.println("Updated PcBuild: " + response.getBody());
    }

    @Test
    @Order(4)
    void testGetAll() {
        String url = BASE_URL + "/getall";
        ResponseEntity<PcBuild[]> response = restTemplate.getForEntity(url, PcBuild[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("All PcBuilds count: " + response.getBody().length);
    }

    @Test
    @Order(5)
    void testDelete() {
        String url = BASE_URL + "/delete/" + pcBuild.getBuildId();
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Boolean.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Boolean.TRUE.equals(response.getBody()));
        System.out.println("Deleted status: " + response.getBody());
    }
}