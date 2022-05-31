package com.bnz.services.auth.utils;

import com.bnz.services.auth.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JWTokenUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.lifeSpan}")
    private long lifeSpan;

    private Claims setTokenClaims(User user, boolean isFresh) {
        Claims claims =  Jwts.claims().setSubject(user.getId());
        claims.put("username", user.getUsername());
        claims.put("role", user.getEmail());
        if(isFresh) {
            claims.put("status", "fresh");
        }
        return claims;
    }
    public Map<String, String> generateToken(User user) {
        long now = System.currentTimeMillis();
        Date avail = new Date(now);
        Date exp = new Date(now + lifeSpan);
        String accessToken = Jwts.builder().setClaims(setTokenClaims(user, true)).setIssuedAt(avail).setExpiration(new Date(now + 3_600_000)).signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
        String refreshToken = Jwts.builder().setClaims(setTokenClaims(user, false)).setIssuedAt(avail).setExpiration(exp).signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
        return Map.ofEntries(
                Map.entry("accessToken", accessToken),
                Map.entry("refreshToken", refreshToken)
        );
    }
}
