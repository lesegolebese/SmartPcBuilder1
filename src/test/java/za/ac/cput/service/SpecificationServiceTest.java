
package za.ac.cput.service;

import org.junit.jupiter.api.*;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Specification;
import za.ac.cput.factory.SpecificationFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpecificationServiceTest {

    @Autowired
    private SpecificationService service;

    private static Specification specification;

    @BeforeAll
    static void init() {
        specification = SpecificationFactory.createSpecification(
                null,
                "RAM",
                "16GB DDR5"
        );

        assertNotNull(specification);
    }

    @Test
    @Order(1)
    void testCreate() {
        specification = service.create(specification);
        assertNotNull(specification);
    }

    @Test
    @Order(2)
    void testRead() {
        Specification read = service.read(specification.getSpecificationId());
        assertNotNull(read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Specification updatedSpecification = new Specification.Builder()
                .copy(specification)
                .setValue("32GB DDR5")
                .build();

        Specification updated = service.update(updatedSpecification);

        assertNotNull(updated);
        assertEquals("32GB DDR5", updated.getValue());
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<Specification> list = service.getAll();
        assertFalse(list.isEmpty());
    }

    @Test
    @Order(5)
    void testDelete() {
        assertTrue(service.delete(specification.getSpecificationId()));
    }
}