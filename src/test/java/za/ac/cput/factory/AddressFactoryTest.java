/* Address.java
   Address POJO entity implementing Builder Pattern
   Author: Lesego Lebese (222371196)
   Date: 27 June 2026 *///

package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import static org.junit.jupiter.api.Assertions.*;

class AddressFactoryTest {
    private Address address;

    @BeforeEach
    void setUp() {
        address = AddressFactory.createAddress(101L, "12 Darling St", "Cape Town CBD", "Cape Town", "Western Cape", "8001", "South Africa");
    }

    @Test
    void testCreateAddress() {
        assertNotNull(address);
        assertEquals(101L, address.getAddressId());
        System.out.println(address.toString());
    }

    @Test
    void testCreateAddressWithMissingData() {
        Address invalidAddress = AddressFactory.createAddress(null, "", "Suburb", "", "Province", "", "South Africa");
        assertNull(invalidAddress, "Factory should return null for missing ID, street, city, or postal code");
        System.out.println("Validation check passed: Invalid address is null.");
    }

    @Test
    void testIdentity() {
        assertInstanceOf(Address.class, address);
    }
}