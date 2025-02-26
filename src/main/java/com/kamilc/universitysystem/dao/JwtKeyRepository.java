package com.kamilc.universitysystem.dao;

import com.kamilc.universitysystem.entity.JwtKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtKeyRepository extends JpaRepository<JwtKey, Integer> {

}
