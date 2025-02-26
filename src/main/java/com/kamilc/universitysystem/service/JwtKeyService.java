package com.kamilc.universitysystem.service;

import com.kamilc.universitysystem.controller.dto.JwtKeyDTO;

public interface JwtKeyService {
    String generateSecretKey();
    JwtKeyDTO saveJwtKey(String secretKey);
}
