/*
 * AdminFactory.java
 * Author: Lesego Lebese (222371196)
 * Date: 10 July 2026
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Admin;
import za.ac.cput.util.Helper;

public class AdminFactory {

    public static Admin createAdmin(String adminId, String firstName, String middleName, String lastName, String email, String password) {
        if (Helper.isNullOrEmpty(adminId) || Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(password)) {
            return null;
        }

        return new Admin.Builder()
                .setAdminId(adminId)
                .setFirstName(firstName)
                .setMiddleName(middleName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .build();
    }
}
