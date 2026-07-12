package za.ac.cput.service;

import za.ac.cput.domain.PcBuildItem;
import java.util.List;

public interface IPcBuildItemService extends Service<PcBuildItem, String> {
    List<PcBuildItem> getAll();
}
