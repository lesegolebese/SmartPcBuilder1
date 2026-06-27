/* Address.java
   Address POJO entity implementing Builder Pattern
   Author: Lesego Lebese (222371196)
   Date: 27 June 2026 *////
package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.User;
import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser("Usr1", "Lesego", "", "Lebese", "lesego@example.com", "SecurePass123", "0712345678");
    }

    @Test
    void testCreateUser() {
        assertNotNull(user);
        assertEquals("Usr1", user.getUserId());
        System.out.println(user.toString());
    }

    @Test
    void testCreateUserWithMissingData() {
        User invalidUser = UserFactory.createUser("", "", "", "Lebese", "", "password", "0712345678");
        assertNull(invalidUser, "Factory should return null if critical user fields are empty strings");
        System.out.println("Validation check passed: Invalid user is null.");
    }

    @Test
    void testIdentity() {
        assertInstanceOf(User.class, user);
    }
}