/**
 * AddressRepository.java
 * Author: Lesego Lebese 222371196
 * Date: 09 July 2026
 */

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Address;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    // Custom query method example to find addresses by city
    List<Address> findByCity(String city);

    List<Address> findByPostalCode(short postalCode);
}