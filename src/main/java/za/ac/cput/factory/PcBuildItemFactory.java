/*
 * PcBuildItemFactory.java
 * Author: Mnelisi Mabona (222062088)
 * Date: 27 June 2026
 */

package za.ac.cput.factory;

import za.ac.cput.domain.PcBuildItem;

public class PcBuildItemFactory {

    public static PcBuildItem createPcBuildItem(Long buildItemId, int quantity) {

        if (buildItemId == null || quantity <= 0) {
            return null;
        }

        return new PcBuildItem.Builder()
                .setBuildItemId(buildItemId)
                .setQuantity(quantity)
                .build();
    }
}