package com.kamilc.universitysystem.dao;

import com.kamilc.universitysystem.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {

}
