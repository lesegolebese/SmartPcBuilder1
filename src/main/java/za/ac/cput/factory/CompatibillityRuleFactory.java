/*
 * CompatibillityRuleFactory.java
 * Author: Lesego Lebese (222371196)
 * Date: 10 July 2026
 */
package za.ac.cput.factory;

import za.ac.cput.domain.CompatibilityRule;
import za.ac.cput.util.Helper;

public class CompatibillityRuleFactory {

    public static CompatibilityRule createCompatibilityRule(String ruleId, String componentType1, String description, String ruledescription) {
        if (Helper.isNullOrEmpty(ruleId) || Helper.isNullOrEmpty(componentType1) || Helper.isNullOrEmpty(description) || Helper.isNullOrEmpty(ruledescription)) {
            return null;
        }

        return new CompatibilityRule.Builder()
                .setRuleId(ruleId)
                .setComponentType1(componentType1)
                .setDescription(description)
                .setRuledescription(ruledescription)
                .build();
    }
}
