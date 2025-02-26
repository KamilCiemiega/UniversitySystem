package com.kamilc.universitysystem.service.serviceImpl;

import com.kamilc.universitysystem.controller.dto.JwtKeyDTO;
import com.kamilc.universitysystem.dao.JwtKeyRepository;
import com.kamilc.universitysystem.entity.JwtKey;
import com.kamilc.universitysystem.mapper.JwtKeyMapper;
import com.kamilc.universitysystem.service.JwtKeyService;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class JwtKeyServiceImpl implements JwtKeyService {

    private final JwtKeyMapper jwtKeyMapper;
    private final JwtKeyRepository jwtKeyRepository;

    @Autowired
    public JwtKeyServiceImpl(JwtKeyMapper jwtKeyMapper, JwtKeyRepository jwtKeyRepository) {
        this.jwtKeyMapper = jwtKeyMapper;
        this.jwtKeyRepository = jwtKeyRepository;
    }

    @Override
    public String generateSecretKey() {
        byte[] keyBytes = new byte[32];
        new SecureRandom().nextBytes(keyBytes);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    @Override
    public JwtKeyDTO saveJwtKey(String secretKey) {
        JwtKey newKey = new JwtKey();
        newKey.setSecretKey(secretKey);
        newKey.setCreatedAt(LocalDateTime.now());
        newKey.setIsActive(true);

        return jwtKeyMapper.toDto(jwtKeyRepository.save(newKey));
    }


}
