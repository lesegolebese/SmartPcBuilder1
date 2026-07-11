package za.ac.cput.service;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService service;

    private static User user;

    @BeforeAll
    static void init() {
        user = UserFactory.createUser(
                "U789",
                "Alice",
                "Jane",
                "Smith",
                "alice.smith@example.com",
                "securePass789",
                "0729876543"
        );
        assertNotNull(user);
    }

    @Test
    @Order(1)
    void testCreate() {
        User created = service.create(user);
        assertNotNull(created);
        assertEquals(user.getUserId(), created.getUserId());
        System.out.println("Service Created User: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        User read = service.read(user.getUserId());
        assertNotNull(read);
        assertEquals(user.getUserId(), read.getUserId());
        System.out.println("Service Read User: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        User updatedUser = new User.Builder().copy(user).setFirstName("AliceUpdated").build();
        User updated = service.update(updatedUser);
        assertNotNull(updated);
        assertEquals("AliceUpdated", updated.getFirstName());
        System.out.println("Service Updated User: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<User> list = service.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        System.out.println("Total Users in Service: " + list.size());
    }

    @Test
    @Order(5)
    void testDelete() {
        boolean deleted = service.delete(user.getUserId());
        assertTrue(deleted);
        User read = service.read(user.getUserId());
        assertNull(read);
        System.out.println("User successfully deleted from Service context.");
    }
}