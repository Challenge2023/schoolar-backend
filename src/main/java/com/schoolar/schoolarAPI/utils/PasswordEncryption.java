package com.schoolar.schoolarAPI.utils;


import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordEncryption {
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean check(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
