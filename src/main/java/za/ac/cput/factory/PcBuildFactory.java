package za.ac.cput.factory;

import za.ac.cput.domain.PcBuild;

public class PcBuildFactory {

    public static PcBuild createPcBuild(String buildId, String buildName, double totalPrice, String buildDescription) {
        if (buildId == null || buildId.isEmpty() || buildName == null || buildName.isEmpty()) {
            return null;
        }

        return new PcBuild.Builder()
                .setBuildId(buildId)
                .setBuildName(buildName)
                .setTotalPrice(totalPrice)
                .setBuildDescription(buildDescription)
                .build();
    }
}