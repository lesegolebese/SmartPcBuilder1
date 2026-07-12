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
        // 💡 Added the 2 missing parameters ("8000" and "South Africa") at the end
        address = AddressFactory.createAddress(
                101L,
                "12 Darling St",
                "District Six",
                "Cape Town",
                "Western Cape",
                "8000",
                "South Africa"
        );
    }

    @Test
    void testCreateAddress() {
        assertNotNull(address);
        assertEquals(101L, address.getAddressId());
        System.out.println(address.toString());
    }

    @Test
    void testCreateAddressFail() {
        // Test validation failure by passing an invalid/null parameter
        Address failedAddress = AddressFactory.createAddress(
                null,
                "",
                "District Six",
                "Cape Town",
                "Western Cape",
                "8000",
                "South Africa"
        );
        assertNull(failedAddress);
    }
}