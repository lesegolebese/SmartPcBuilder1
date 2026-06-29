/*
 * AddressFactory.java
 * Author: Lesego Lebese 222371196
 * Date: 27 June 2026
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;

public class AddressFactory {

    public static Address createAddress(Long addressId, String streetName, String suburb, String city, String province, String postalCode, String country) {

        if (addressId == null || Helper.isNullOrEmpty(streetName) || Helper.isNullOrEmpty(city) || Helper.isNullOrEmpty(postalCode) || Helper.isNullOrEmpty(country)) {
            return null;
        }

        return new Address.Builder()
                .setAddressId(addressId)
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setCity(city)
                .setProvince(province)
                .setPostalCode(postalCode)
                .setCountry(country)
                .build();
    }
}
