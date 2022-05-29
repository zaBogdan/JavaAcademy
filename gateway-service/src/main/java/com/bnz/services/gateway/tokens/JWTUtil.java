package com.bnz.services.gateway.tokens;

import com.bnz.services.gateway.exceptions.JwtTokenMalformedException;
import com.bnz.services.gateway.exceptions.JwtTokenMissingException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.lifeSpan}")
    private long lifeSpan;

    public Claims getClaims(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            log.error("Failed to get claims of JWT: "+ e.getMessage());
        }
        return null;
    }

    // TODO: Change this when you have a user model
    public String generateToken(String id) {
        Claims claims =  Jwts.claims().setSubject(id);
        long now = System.currentTimeMillis();
        Date avail = new Date(now);
        Date exp = new Date(now + lifeSpan);
        return Jwts.builder().setClaims(claims).setIssuedAt(avail).setExpiration(exp).signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
    }

    public void validateToken(String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
