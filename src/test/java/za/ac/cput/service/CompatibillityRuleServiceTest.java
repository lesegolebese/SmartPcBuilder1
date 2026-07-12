/*
 * CompatibillityRuleServiceTest.java
 * Author: Matinisa Lubisi (222527269)
 * Date: 12 July 2026
 */

package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CompatibilityRule;
import za.ac.cput.factory.CompatibillityRuleFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompatibillityRuleServiceTest {

    @Autowired
    private CompatibillityRuleService service;

    private static CompatibilityRule compatibilityRule;

    @BeforeAll
    static void init() {
        compatibilityRule = CompatibillityRuleFactory.createCompatibilityRule(
                "RULE001",
                "GPU",
                "RTX 4090 compatibility",
                "Compatible with 850W+ PSU"
        );
        assertNotNull(compatibilityRule);
    }

    @Test
    @Order(1)
    void testCreate() {
        CompatibilityRule created = service.create(compatibilityRule);
        assertNotNull(created);
        assertEquals(compatibilityRule.getRuleId(), created.getRuleId());
        System.out.println("Service Created Rule: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        CompatibilityRule read = service.read(compatibilityRule.getRuleId());
        assertNotNull(read);
        assertEquals(compatibilityRule.getRuleId(), read.getRuleId());
        System.out.println("Service Read Rule: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        CompatibilityRule updatedRule = new CompatibilityRule.Builder()
                .copy(compatibilityRule)
                .setDescription("RTX 4090 Updated compatibility")
                .build();
        CompatibilityRule updated = service.update(updatedRule);
        assertNotNull(updated);
        assertEquals("RTX 4090 Updated compatibility", updated.getDescription());
        System.out.println("Service Updated Rule: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<CompatibilityRule> list = service.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        System.out.println("Total Rules in Service: " + list.size());
    }

    @Test
    @Order(5)
    void testFindByComponentType1() {
        List<CompatibilityRule> found = service.findByComponentType1(compatibilityRule.getComponentType1());
        assertNotNull(found);
        assertTrue(found.size() > 0);
        System.out.println("Service Found Rules by ComponentType1: " + found.size());
    }

    @Test
    @Order(6)
    void testFindByDescription() {
        List<CompatibilityRule> found = service.findByDescription(compatibilityRule.getDescription());
        assertNotNull(found);
        assertTrue(found.size() > 0);
        System.out.println("Service Found Rules by Description: " + found.size());
    }

    @Test
    @Order(7)
    void testFindByRuleId() {
        CompatibilityRule found = service.findByRuleId(compatibilityRule.getRuleId());
        assertNotNull(found);
        assertEquals(compatibilityRule.getRuleId(), found.getRuleId());
        System.out.println("Service Found Rule by RuleId: " + found);
    }

    @Test
    @Order(8)
    void testDelete() {
        boolean deleted = service.delete(compatibilityRule.getRuleId());
        assertTrue(deleted);
        CompatibilityRule read = service.read(compatibilityRule.getRuleId());
        assertNull(read);
        System.out.println("Rule successfully deleted from Service context.");
    }
}
