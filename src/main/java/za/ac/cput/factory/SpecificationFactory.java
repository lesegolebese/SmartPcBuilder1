package za.ac.cput.factory;

import za.ac.cput.domain.Specification;
import za.ac.cput.util.Helper;

public class SpecificationFactory {

    public static Specification createSpecification(Long specificationId,
                                                    String key,
                                                    String value) {

        if (specificationId == null ||
                Helper.isNullOrEmpty(key) ||
                Helper.isNullOrEmpty(value)) {
            return null;
        }

        return new Specification.Builder()
                .setSpecificationId(specificationId)
                .setKey(key)
                .setValue(value)
                .build();
    }
}