package za.ac.cput.service;

import za.ac.cput.domain.PcBuild;
import java.util.List;

public interface IPcBuildService extends Service<PcBuild, String> {
    List<PcBuild> getAll();
}