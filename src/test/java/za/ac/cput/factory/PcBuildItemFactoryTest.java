/*
 * PcBuildItemFactoryTest.java
 * Author: Mnelisi Mabona (222062088)
 * Date: 27 June 2026
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.PcBuildItem;

import static org.junit.jupiter.api.Assertions.*;

class PcBuildItemFactoryTest {

    private PcBuildItem pcBuildItem;

    @BeforeEach
    void setUp() {
        pcBuildItem = PcBuildItemFactory.createPcBuildItem(501L, 2);
    }

    @Test
    void testCreatePcBuildItem() {
        assertNotNull(pcBuildItem);
        assertEquals(501L, pcBuildItem.getBuildItemId());
        assertEquals(2, pcBuildItem.getQuantity());

        System.out.println(pcBuildItem);
    }

    @Test
    void testCreatePcBuildItemWithMissingData() {

        PcBuildItem invalidBuildItem =
                PcBuildItemFactory.createPcBuildItem(null, 0);

        assertNull(invalidBuildItem,
                "Factory should return null for missing build item ID or invalid quantity.");

        System.out.println("Validation check passed: Invalid PC Build Item is null.");
    }

    @Test
    void testIdentity() {
        assertInstanceOf(PcBuildItem.class, pcBuildItem);
    }
}