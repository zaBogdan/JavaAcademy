package com.bnz.shared.security.passwords;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptHash implements HashPassword {
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public static String hash(String password) {
        return encoder.encode(password);
    }
    public static boolean verify(String hash, String plainText) {
        return encoder.matches(plainText,hash);
    }
}
