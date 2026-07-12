package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.PcBuild;

import static org.junit.jupiter.api.Assertions.*;

class PcBuildFactoryTest {

    private PcBuild pcBuild;

    @BeforeEach
    void setUp() {
        // Aligned with your 4-argument factory: String, String, double, String
        pcBuild = PcBuildFactory.createPcBuild(
                "201",
                "Gaming PC",
                25500.00,
                "High-end gaming setup with RTX 4070"
        );
    }

    @Test
    void testCreatePcBuild() {
        assertNotNull(pcBuild);
        assertEquals("201", pcBuild.getBuildId());
        assertEquals("Gaming PC", pcBuild.getBuildName());
        assertEquals(25500.00, pcBuild.getTotalPrice());
        System.out.println("Factory Created PcBuild Successfully: " + pcBuild);
    }

    @Test
    void testCreatePcBuildWithNull() {
        PcBuild invalidBuild = PcBuildFactory.createPcBuild(null, "", 0.0, "");
        assertNull(invalidBuild);
    }
}