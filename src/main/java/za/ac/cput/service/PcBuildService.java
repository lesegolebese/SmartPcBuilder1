package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.PcBuild;
import za.ac.cput.repository.PcBuildRepository;

import java.util.List;

@Service
public class PcBuildService implements IPcBuildService {

    private final PcBuildRepository repository;

    @Autowired
    public PcBuildService(PcBuildRepository repository) {
        this.repository = repository;
    }

    @Override
    public PcBuild create(PcBuild pcBuild) {
        return repository.save(pcBuild);
    }

    @Override
    public PcBuild read(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public PcBuild update(PcBuild pcBuild) {
        if (repository.existsById(pcBuild.getBuildId())) {
            return repository.save(pcBuild);
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
    public List<PcBuild> getAll() {
        return repository.findAll();
    }
}