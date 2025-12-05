package com.dao;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {

    // Generate a random salt
    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hash the password with PBKDF2
    public static String hashPassword(String password, String salt) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                Base64.getDecoder().decode(salt),
                65536,      // iterations
                256         // key length
        );

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hash);
    }

    // Verify a password
    public static boolean verifyPassword(String password, String salt, String expectedHash)
            throws Exception {

        String hash = hashPassword(password, salt);
        return hash.equals(expectedHash);
    }
}
