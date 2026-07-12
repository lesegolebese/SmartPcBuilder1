

package za.ac.cput.service;

import org.junit.jupiter.api.*;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Category;
import za.ac.cput.factory.CategoryFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryServiceTest {

    @Autowired
    private CategoryService service;

    private static Category category;

    @BeforeAll
    static void init() {
        category = CategoryFactory.createCategory(
                null,
                "Laptops",
                "Gaming laptops"
        );

        assertNotNull(category);
    }

    @Test
    @Order(1)
    void testCreate() {
        category = service.create(category);
        assertNotNull(category);
    }

    @Test
    @Order(2)
    void testRead() {
        Category read = service.read(category.getCategoryId());
        assertNotNull(read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Category updatedCategory = new Category.Builder()
                .copy(category)
                .setDescription("Office and Gaming Laptops")
                .build();

        Category updated = service.update(updatedCategory);

        assertNotNull(updated);
        assertEquals("Office and Gaming Laptops", updated.getDescription());
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<Category> list = service.getAll();
        assertFalse(list.isEmpty());
    }

    @Test
    @Order(5)
    void testDelete() {
        assertTrue(service.delete(category.getCategoryId()));
    }
}