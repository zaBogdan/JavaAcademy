package com.bnz.shared.security.tokens;

import com.bnz.shared.exceptions.JwtTokenMalformedException;
import com.bnz.shared.exceptions.JwtTokenMissingException;
import com.bnz.shared.models.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTokenHandler {

    @Value("${globals.jwt.secret}")
    private String jwtSecret;

    @Value("${globals.jwt.freshLifeSpan}")
    private long freshLifeSpan;

    @Value("${globals.jwt.refreshLifeSpan}")
    private long refreshLifeSpan;

    public String getToken(HttpHeaders headers) {
        String[] data = headers.getOrEmpty("Authorization").get(0).split(" ");
        if(!data[0].equals("Bearer"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token type must be Bearer");
        return data[1];
    }

    public void validate(HttpHeaders headers) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(getToken(headers));
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
        } catch(ResponseStatusException ex) {
            throw new ResponseStatusException(ex.getStatus(), ex.getReason());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Claims getDataFromTokens(HttpHeaders headers) {
        final String token = getToken(headers);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims;
    }

    public Map<String, Object> getTokenData(HttpHeaders headers) {
        return new HashMap<>(getDataFromTokens(headers));
    }

    public Map<String, String> generateTokenPair(User user) {
        long now = System.currentTimeMillis();
        Date avail = new Date(now);
        Date exp = new Date(now + refreshLifeSpan);
        String accessToken = Jwts.builder().setClaims(setTokenClaims(user, true)).setIssuedAt(avail).setExpiration(new Date(now + freshLifeSpan)).signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
        String refreshToken = Jwts.builder().setClaims(setTokenClaims(user, false)).setIssuedAt(avail).setExpiration(exp).signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
        return Map.ofEntries(
            Map.entry("accessToken", accessToken),
            Map.entry("refreshToken", refreshToken)
        );
    }

    private Claims setTokenClaims(User user, boolean freshness) {
        Claims claims = Jwts.claims();
        claims.setSubject(user.getId());
        claims.put("username", user.getUsername());
        claims.put("role", 10110);
        claims.put("fresh", freshness);
        return claims;
    }
}
