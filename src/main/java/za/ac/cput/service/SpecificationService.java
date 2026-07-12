



package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Specification;
import za.ac.cput.repository.SpecificationRepository;

import java.util.List;

@Service
public class SpecificationService implements ISpecificationService {

    private final SpecificationRepository repository;

    @Autowired
    public SpecificationService(SpecificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Specification create(Specification specification) {
        return repository.save(specification);
    }

    @Override
    public Specification read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Specification update(Specification specification) {
        if (repository.existsById(specification.getSpecificationId())) {
            return repository.save(specification);
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
    public List<Specification> getAll() {
        return repository.findAll();
    }
}