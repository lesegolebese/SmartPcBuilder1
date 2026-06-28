package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Specification;

import static org.junit.jupiter.api.Assertions.*;

class SpecificationFactoryTest {

    private Specification specification;

    @BeforeEach
    void setUp() {
        specification = SpecificationFactory.createSpecification(
                1L,
                "Color",
                "Black"
        );
    }

    @Test
    void testCreateSpecification() {
        assertNotNull(specification);
        assertEquals("Color", specification.getKey());
        System.out.println(specification);
    }

    @Test
    void testCreateSpecificationWithMissingData() {
        Specification invalid = SpecificationFactory.createSpecification(
                null,
                "",
                ""
        );

        assertNull(invalid);
    }

    @Test
    void testIdentity() {
        assertInstanceOf(Specification.class, specification);
    }
}