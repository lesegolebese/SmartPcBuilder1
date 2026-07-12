package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.PcBuild;
import za.ac.cput.factory.PcBuildFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PcBuildServiceTest {

    @Autowired
    private PcBuildService service;

    private static PcBuild pcBuild;

    @BeforeAll
    static void init() {
        pcBuild = PcBuildFactory.createPcBuild(
                "Bld-001",
                "Custom Office Rig",
                12500.00,
                "A budget workstation build for processing documents."
        );
        assertNotNull(pcBuild);
    }

    @Test
    @Order(1)
    void testCreate() {
        PcBuild created = service.create(pcBuild);
        assertNotNull(created);
        System.out.println("Service Created PcBuild: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        PcBuild read = service.read(pcBuild.getBuildId());
        assertNotNull(read);
        System.out.println("Service Read PcBuild: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        PcBuild updatedPcBuild = new PcBuild.Builder().copy(pcBuild).setBuildName("Ultimate Office Rig").build();
        PcBuild updated = service.update(updatedPcBuild);
        assertNotNull(updated);
        assertEquals("Ultimate Office Rig", updated.getBuildName());
        System.out.println("Service Updated PcBuild: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<PcBuild> list = service.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        System.out.println("Total PcBuilds in Service: " + list.size());
    }

    @Test
    @Order(5)
    void testDelete() {
        boolean deleted = service.delete(pcBuild.getBuildId());
        assertTrue(deleted);
        PcBuild read = service.read(pcBuild.getBuildId());
        assertNull(read);
        System.out.println("PcBuild successfully deleted from Service context.");
    }
}