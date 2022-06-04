package com.bnz.shared.security.passwords;

public interface HashPassword {
    static String hash(String password) {
        return "";
    }

    static boolean verify(String hash, String plainText){
        return false;
    }
}
