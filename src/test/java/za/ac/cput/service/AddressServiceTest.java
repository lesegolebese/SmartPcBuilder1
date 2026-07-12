package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.Main;
import za.ac.cput.domain.Address;
import za.ac.cput.factory.AddressFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceTest {

    @Autowired
    private AddressService service;

    private static Address address;

    @BeforeAll
    static void init() {
        address = AddressFactory.createAddress(
                2L,
                "99 Loop St",
                "Central Business District",
                "Cape Town",
                "Western Cape",  // Province
                "8000",          // Postal Code (Added)
                "South Africa"   // Country (Added)
        );
        assertNotNull(address);
    }

    @Test
    @Order(1)
    void testCreate() {
        Address created = service.create(address);
        assertNotNull(created);
        System.out.println("Service Created Address: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        Address read = service.read(address.getAddressId());
        assertNotNull(read);
        System.out.println("Service Read Address: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Address updatedAddress = new Address.Builder().copy(address).setStreetName("101 Loop St").build();
        Address updated = service.update(updatedAddress);
        assertNotNull(updated);
        assertEquals("101 Loop St", updated.getStreetName());
        System.out.println("Service Updated Address: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<Address> list = service.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        System.out.println("Total Addresses in Service: " + list.size());
    }

    @Test
    @Order(5)
    void testDelete() {
        boolean deleted = service.delete(address.getAddressId());
        assertTrue(deleted);
        Address read = service.read(address.getAddressId());
        assertNull(read);
        System.out.println("Address successfully deleted from Service context.");
    }
}