/*
 * ComapatibillityRuleFactoryTest.java
 * Author: Matinisa Lubisi (222527269)
 * Date: 12 July 2026
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.CompatibilityRule;

import static org.junit.jupiter.api.Assertions.*;

class ComapatibillityRuleFactoryTest {
    private CompatibilityRule compatibilityRule;

    @BeforeEach
    void setUp() {
        compatibilityRule = CompatibillityRuleFactory.createCompatibilityRule(1L, "GPU", "RTX 4090 compatibility", "Compatible with 850W+ PSU");
    }

    @Test
    void testCreateCompatibilityRule() {
        assertNotNull(compatibilityRule);
        assertEquals(1L, compatibilityRule.getRuleId());
        assertEquals("GPU", compatibilityRule.getComponentType1());
        assertEquals("RTX 4090 compatibility", compatibilityRule.getDescription());
        assertEquals("Compatible with 850W+ PSU", compatibilityRule.getRuledescription());
        System.out.println(compatibilityRule.toString());
    }

    @Test
    void testCreateCompatibilityRuleWithMissingData() {
        CompatibilityRule invalidRule = CompatibillityRuleFactory.createCompatibilityRule(null, "", "", "");
        assertNull(invalidRule, "Factory should return null if critical rule fields are empty strings");
        System.out.println("Validation check passed: Invalid rule is null.");
    }

    @Test
    void testCreateCompatibilityRuleWithNullFields() {
        CompatibilityRule invalidRule = CompatibillityRuleFactory.createCompatibilityRule(2L, null, "description", "ruledescription");
        assertNull(invalidRule, "Factory should return null if componentType1 is null");
        System.out.println("Validation check passed: Rule with null componentType1 is null.");
    }

    @Test
    void testIdentity() {
        assertInstanceOf(CompatibilityRule.class, compatibilityRule);
    }
}
