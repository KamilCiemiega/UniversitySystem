package com.kamilc.universitysystem.domain.dao;

import com.kamilc.universitysystem.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
