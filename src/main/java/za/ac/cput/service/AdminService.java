package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.repository.AdminRepository;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    private final AdminRepository repository;

    @Autowired
    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Admin create(Admin admin) {
        return repository.save(admin);
    }

    @Override
    public Admin read(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        if (repository.existsById(admin.getAdminId())) {
            return repository.save(admin);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return !repository.existsById(id);
        }
        return false;
    }

    @Override
    public List<Admin> getAll() {
        return repository.findAll();
    }

    @Override
    public Admin findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public Admin findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password).orElse(null);
    }
}
