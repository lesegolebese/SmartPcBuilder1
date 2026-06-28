/*
 * PcBuildFactory.java
 * Author: Mnelisi Mabona (222062088)
 * Date: 27 June 2026
 */

package za.ac.cput.factory;

import za.ac.cput.domain.PcBuild;

import java.time.LocalDate;

public class PcBuildFactory {

    public static PcBuild createPcBuild(Long buildId,
                                        String buildName,
                                        LocalDate createdDate) {

        if (buildId == null || buildName == null || buildName.isEmpty() || createdDate == null) {
            return null;
        }

        return new PcBuild.Builder()
                .setBuildId(buildId)
                .setBuildName(buildName)
                .setCreatedDate(createdDate)
                .build();
    }
}