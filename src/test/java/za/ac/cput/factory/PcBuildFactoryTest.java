/*
 * PcBuildFactoryTest.java
 * Author: Mnelisi Mabona (222062088)
 * Date: 27 June 2026
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.PcBuild;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PcBuildFactoryTest {

    private PcBuild pcBuild;

    @BeforeEach
    void setUp() {
        pcBuild = PcBuildFactory.createPcBuild(
                201L,
                "Gaming PC",
                LocalDate.of(2026, 6, 27)
        );
    }

    @Test
    void testCreatePcBuild() {
        assertNotNull(pcBuild);
        assertEquals(201L, pcBuild.getBuildId());
        assertEquals("Gaming PC", pcBuild.getBuildName());

        System.out.println(pcBuild);
    }

    @Test
    void testCreatePcBuildWithMissingData() {

        PcBuild invalidBuild = PcBuildFactory.createPcBuild(
                null,
                null,
                LocalDate.now()
        );

        assertNull(invalidBuild,
                "Factory should return null for missing build ID or build name.");
    }

    @Test
    void testIdentity() {
        assertInstanceOf(PcBuild.class, pcBuild);
    }
}