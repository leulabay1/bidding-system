package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {

    public static String hashPassword(String plainPassword) {
        // Hash a password using BCrypt
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        // Verify a password using BCrypt
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}