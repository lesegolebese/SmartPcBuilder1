package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryFactoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = CategoryFactory.createCategory(
                1L,
                "Electronics",
                "Electronic Devices"
        );
    }

    @Test
    void testCreateCategory() {
        assertNotNull(category);
        assertEquals("Electronics", category.getName());
        System.out.println(category);
    }

    @Test
    void testCreateCategoryWithMissingData() {
        Category invalid = CategoryFactory.createCategory(
                null,
                "",
                ""
        );

        assertNull(invalid);
    }

    @Test
    void testIdentity() {
        assertInstanceOf(Category.class, category);
    }
}