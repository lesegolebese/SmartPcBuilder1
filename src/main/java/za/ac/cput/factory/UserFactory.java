/*
 * UserFactory.java
 * Author: Lesego Lebese (222371196)
 * Date: 27 June 2026
 */
package za.ac.cput.factory;

import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class UserFactory {

    public static User createUser(String userId, String firstName, String middleName, String lastName, String email, String password, String phoneNumber) {
        if (Helper.isNullOrEmpty(userId) || Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(password)) {
            return null;
        }

        return new User.Builder()
                .setUserId(userId)
                .setFirstName(firstName)
                .setMiddleName(middleName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setPhoneNumber(phoneNumber)
                .build();
    }
}
