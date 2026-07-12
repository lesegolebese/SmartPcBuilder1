
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Category;
import za.ac.cput.repository.CategoryRepository;

import java.util.List;
//
@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public Category read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Category update(Category category) {
        if (repository.existsById(category.getCategoryId())) {
            return repository.save(category);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return !repository.existsById(id);
        }
        return false;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }
}