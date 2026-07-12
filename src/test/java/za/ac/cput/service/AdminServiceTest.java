/*
 * AdminServiceTest.java
 * Author: Matinisa Lubisi (222527269)
 * Date: 12 July 2026
 */

package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.AdminFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminServiceTest {

    @Autowired
    private AdminService service;

    private static Admin admin;

    @BeforeAll
    static void init() {
        admin = AdminFactory.createAdmin(
                "ADM001",
                "John",
                "David",
                "Smith",
                "john.smith@example.com",
                "SecurePass123"
        );
        assertNotNull(admin);
    }

    @Test
    @Order(1)
    void testCreate() {
        Admin created = service.create(admin);
        assertNotNull(created);
        assertEquals(admin.getAdminId(), created.getAdminId());
        System.out.println("Service Created Admin: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        Admin read = service.read(admin.getAdminId());
        assertNotNull(read);
        assertEquals(admin.getAdminId(), read.getAdminId());
        System.out.println("Service Read Admin: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Admin updatedAdmin = new Admin.Builder().copy(admin).setFirstName("JohnUpdated").build();
        Admin updated = service.update(updatedAdmin);
        assertNotNull(updated);
        assertEquals("JohnUpdated", updated.getFirstName());
        System.out.println("Service Updated Admin: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<Admin> list = service.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        System.out.println("Total Admins in Service: " + list.size());
    }

    @Test
    @Order(5)
    void testFindByEmail() {
        Admin found = service.findByEmail(admin.getEmail());
        assertNotNull(found);
        assertEquals(admin.getEmail(), found.getEmail());
        System.out.println("Service Found Admin by Email: " + found);
    }

    @Test
    @Order(6)
    void testFindByEmailAndPassword() {
        Admin found = service.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
        assertNotNull(found);
        assertEquals(admin.getEmail(), found.getEmail());
        System.out.println("Service Found Admin by Email and Password: " + found);
    }

    @Test
    @Order(7)
    void testDelete() {
        boolean deleted = service.delete(admin.getAdminId());
        assertTrue(deleted);
        Admin read = service.read(admin.getAdminId());
        assertNull(read);
        System.out.println("Admin successfully deleted from Service context.");
    }
}
