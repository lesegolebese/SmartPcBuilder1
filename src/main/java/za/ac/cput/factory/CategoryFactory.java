package za.ac.cput.factory;

import za.ac.cput.domain.Category;
import za.ac.cput.util.Helper;

public class CategoryFactory {

    public static Category createCategory(Long categoryId,
                                          String name,
                                          String description) {

        if (categoryId == null ||
                Helper.isNullOrEmpty(name) ||
                Helper.isNullOrEmpty(description)) {
            return null;
        }

        return new Category.Builder()
                .setCategoryId(categoryId)
                .setName(name)
                .setDescription(description)
                .build();
    }
}