package za.ac.cput.factory;

import za.ac.cput.domain.PcBuildItem;

public class PcBuildItemFactory {

    public static PcBuildItem createPcBuildItem(String buildItemId, String componentName, int quantity, double unitPrice) {
        if (buildItemId == null || buildItemId.isEmpty() || quantity <= 0) {
            return null;
        }

        return new PcBuildItem.Builder()
                .setBuildItemId(buildItemId)
                .setComponentName(componentName)
                .setQuantity(quantity)
                .setUnitPrice(unitPrice)
                .build();
    }
}