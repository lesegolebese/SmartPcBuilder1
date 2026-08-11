package za.ac.cput.factory;

import za.ac.cput.domain.Specification;
import za.ac.cput.util.Helper;

public class SpecificationFactory {

    public static Specification createSpecification(Long specificationId,
                                                    String specKey,
                                                    String specValue) {

        if (Helper.isNullOrEmpty(specKey) || Helper.isNullOrEmpty(specValue)) {
            return null;
        }

        return new Specification.Builder()
                .setSpecificationId(specificationId)
                .setSpecKey(specKey)
                .setSpecValue(specValue)
                .build();
    }
}