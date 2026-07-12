package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.PcBuildItem;

import static org.junit.jupiter.api.Assertions.*;

class PcBuildItemFactoryTest {

    private PcBuildItem pcBuildItem;

    @BeforeEach
    void setUp() {
        // Line up all 4 expected parameters: String, String, int, double
        pcBuildItem = PcBuildItemFactory.createPcBuildItem(
                "501",
                "NVIDIA RTX 4060 Ti",
                1,
                8500.00
        );
    }

    @Test
    void testCreatePcBuildItem() {
        assertNotNull(pcBuildItem);
        assertEquals("501", pcBuildItem.getBuildItemId());
        assertEquals("NVIDIA RTX 4060 Ti", pcBuildItem.getComponentName());
        assertEquals(1, pcBuildItem.getQuantity());
        assertEquals(8500.00, pcBuildItem.getUnitPrice());
        System.out.println("Factory Created PcBuildItem: " + pcBuildItem);
    }

    @Test
    void testCreatePcBuildItemWithMissingData() {
        // Line 37 fixed here by providing all 4 required arguments
        PcBuildItem invalidBuildItem = PcBuildItemFactory.createPcBuildItem(
                null,
                "Invalid Component",
                0,
                0.0
        );

        assertNull(invalidBuildItem, "Factory should return null for missing build item ID or invalid quantity.");
        System.out.println("Validation check passed: Invalid PC Build Item is null.");
    }
}