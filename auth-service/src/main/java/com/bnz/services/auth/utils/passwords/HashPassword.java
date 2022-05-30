package com.bnz.services.auth.utils.passwords;

public interface HashPassword {
    static String hash(String password) {
        return "";
    }

    static boolean verify(String hash, String plainText){
        return false;
    }
}
