package com.bnz.services.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTokenUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.lifeSpan}")
    private long lifeSpan;

    public String generateToken(String id) {
        Claims claims =  Jwts.claims().setSubject(id);
        long now = System.currentTimeMillis();
        Date avail = new Date(now);
        Date exp = new Date(now + lifeSpan);
        return Jwts.builder().setClaims(claims).setIssuedAt(avail).setExpiration(exp).signWith(SignatureAlgorithm.HS256, "helloWorld").compact();
    }
}
