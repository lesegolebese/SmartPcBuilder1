package za.ac.cput.service;

import za.ac.cput.domain.PcBuildItem;
import java.util.List;

public interface IPcBuildItemService extends IService<PcBuildItem, String> {
    List<PcBuildItem> getAll();
}
