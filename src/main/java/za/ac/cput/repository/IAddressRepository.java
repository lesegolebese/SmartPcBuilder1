/**
 * AddressRepository.java
 * Author: Lesego Lebese 222371196
 * Date: 09 July 2026
 */

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Address;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {

}