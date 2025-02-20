package com.kamilc.universitysystem.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.security.Key;
import java.util.Base64;

@Component
public class JwtUtil {

//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    public Key getSecretKey() {
//        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
//        return Keys.hmacShaKeyFor(decodedKey);
//    }
}
