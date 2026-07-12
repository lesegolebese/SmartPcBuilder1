package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.PcBuildItem;
import za.ac.cput.repository.PcBuildItemRepository;

import java.util.List;

@Service
public class PcBuildItemService implements IPcBuildItemService {

    private final PcBuildItemRepository repository;

    @Autowired
    public PcBuildItemService(PcBuildItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public PcBuildItem create(PcBuildItem pcBuildItem) {
        return repository.save(pcBuildItem);
    }

    @Override
    public PcBuildItem read(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public PcBuildItem update(PcBuildItem pcBuildItem) {
        if (repository.existsById(pcBuildItem.getBuildItemId())) {
            return repository.save(pcBuildItem);
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
    public List<PcBuildItem> getAll() {
        return repository.findAll();
    }
}