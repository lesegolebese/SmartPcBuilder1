package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.PcBuildItem;
import za.ac.cput.factory.PcBuildItemFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PcBuildItemServiceTest {

    @Autowired
    private PcBuildItemService service;

    private static PcBuildItem pcBuildItem;

    @BeforeAll
    static void init() {
        pcBuildItem = PcBuildItemFactory.createPcBuildItem(
                "Itm-992",
                "AMD Ryzen 5 5600X",
                1,
                3800.00
        );
        assertNotNull(pcBuildItem);
    }

    @Test
    @Order(1)
    void testCreate() {
        PcBuildItem created = service.create(pcBuildItem);
        assertNotNull(created);
        System.out.println("Service Created PcBuildItem: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        PcBuildItem read = service.read(pcBuildItem.getBuildItemId());
        assertNotNull(read);
        System.out.println("Service Read PcBuildItem: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        PcBuildItem updatedPcBuildItem = new PcBuildItem.Builder().copy(pcBuildItem).setQuantity(2).build();
        PcBuildItem updated = service.update(updatedPcBuildItem);
        assertNotNull(updated);
        assertEquals(2, updated.getQuantity());
        System.out.println("Service Updated PcBuildItem: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<PcBuildItem> list = service.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        System.out.println("Total PcBuildItems in Service: " + list.size());
    }

    @Test
    @Order(5)
    void testDelete() {
        boolean deleted = service.delete(pcBuildItem.getBuildItemId());
        assertTrue(deleted);
        PcBuildItem read = service.read(pcBuildItem.getBuildItemId());
        assertNull(read);
        System.out.println("PcBuildItem successfully deleted from Service context.");
    }
}