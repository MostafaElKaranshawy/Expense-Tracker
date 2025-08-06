package com.project.project.services;

import com.project.project.Models.Token;
import com.project.project.Models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    static String SECRET_KEY;

    public JwtService(@Value("${jwt.secret}") String secretKey) {
        SECRET_KEY = secretKey;
    }

    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("username", user.getUsername());
        return Jwts.builder()
                .claims()
                .add(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 )) // 1 hour
                .and()
                .signWith(getKey())
                .compact();
    }

    private static Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    public String extractUserNameFromToken(String token) {
        return extractAllClaims(token).getUsername();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }
    private Token extractAllClaims(String token) {
        Map<String, Object> claims = Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        Token tokenObject = new Token();
        tokenObject.setUserId((Integer) claims.get("userId"));
        tokenObject.setUsername((String) claims.get("username"));
        long expTimestamp = ((Number) claims.get("exp")).longValue();
        Date expirationDate = new Date(expTimestamp * 1000); // convert seconds → millis
        tokenObject.setExpiration(expirationDate);
        tokenObject.setToken(token);
        return tokenObject;
    }
}
