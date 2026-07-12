/*
 * AdminFactoryTest.java
 * Author: Matinisa Lubisi (222527269)
 * Date: 12 July 2026
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Admin;

import static org.junit.jupiter.api.Assertions.*;

class AdminFactoryTest {
    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = AdminFactory.createAdmin("ADM001", "John", "David", "Smith", "john.smith@example.com", "SecurePass123");
    }

    @Test
    void testCreateAdmin() {
        assertNotNull(admin);
        assertEquals("ADM001", admin.getAdminId());
        assertEquals("John", admin.getFirstName());
        assertEquals("David", admin.getMiddleName());
        assertEquals("Smith", admin.getLastName());
        assertEquals("john.smith@example.com", admin.getEmail());
        assertEquals("SecurePass123", admin.getPassword());
        System.out.println(admin.toString());
    }

    @Test
    void testCreateAdminWithMissingData() {
        Admin invalidAdmin = AdminFactory.createAdmin("", "", "", "", "", "");
        assertNull(invalidAdmin, "Factory should return null if critical admin fields are empty strings");
        System.out.println("Validation check passed: Invalid admin is null.");
    }

    @Test
    void testCreateAdminWithNullFields() {
        Admin invalidAdmin = AdminFactory.createAdmin("ADM002", "Jane", "Marie", "Doe", null, "password");
        assertNull(invalidAdmin, "Factory should return null if email is null");
        System.out.println("Validation check passed: Admin with null email is null.");
    }

    @Test
    void testIdentity() {
        assertInstanceOf(Admin.class, admin);
    }
}
