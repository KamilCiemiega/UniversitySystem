package com.kamilc.universitysystem.service.serviceImpl;

import com.kamilc.universitysystem.dao.TokenRepository;
import com.kamilc.universitysystem.security.JwtUtil;
import com.kamilc.universitysystem.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository, JwtUtil jwtUtil) {
        this.tokenRepository = tokenRepository;
        this.jwtUtil = jwtUtil;
    }

//    @Override
//    public String saveAccessToken(String email) {
//       String token = jwtUtil.generateAccessToken(email);
//
//        Token accessToken = new Token();
//        accessToken.setToken(token);
//        accessToken.setTokenType(Token.TokenType.ACCESS);
//        accessToken.setExpireDate();
//        accessToken.setCreatedAt(Timestamp.from(Instant.now()));
//    }

    @Override
    public String saveAccessToken() {
        return "";
    }

    @Override
    public String saveRefreshToken() {
        return "";
    }
}
