/* Address.java
   Address POJO entity implementing Builder Pattern
   Author: Lesego Lebese (222371196 )
   Date: 27 June  2026 *///
package za.ac.cput.util;


public class Helper {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isValidPostalCode(short postalCode) {
        if (postalCode < 1000 || postalCode > 9999) {
            return false;
        }
        return true;
    }

    //
    public static boolean isValidStreetNumber(short streetNumber) {
        if (streetNumber < 1 || streetNumber > 99999) {
            return false;
        }
        return true;
    }

    public static boolean isValidErfNumber(int erfNumber) {
        if (erfNumber < 1 || erfNumber > 99999) {
            return false;
        }
        return true;
    }
    public static boolean isValidUnitNumber(short unitNumber) {
        if (unitNumber < 1 || unitNumber > 99999) {
            return false;
        }
        return true;
    }
}

