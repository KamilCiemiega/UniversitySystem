package com.kamilc.universitysystem.security;

import com.kamilc.universitysystem.service.JwtKeyService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final JwtKeyService jwtKeyService;
    private static final long ACCESS_EXPIRATION = 900000; // 15 min
    private static final long REFRESH_EXPIRATION = 604800000; // 7 days
    private Key SIGNING_KEY;

    @Autowired
    public JwtUtil(JwtKeyService jwtKeyService) {
        this.jwtKeyService = jwtKeyService;
    }


    private Key getSigninKey(){
        String secret = jwtKeyService.generateSecretKey();
        jwtKeyService.saveJwtKey(secret);
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        Key key = Keys.hmacShaKeyFor(decodedKey);
        this.SIGNING_KEY = key;

        return key;
    }

    public String generateAccessToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION))
                .signWith(getSigninKey())
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION))
                .signWith(getSigninKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
}
