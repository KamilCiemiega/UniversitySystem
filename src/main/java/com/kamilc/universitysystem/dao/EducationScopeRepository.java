package com.kamilc.universitysystem.dao;

import com.kamilc.universitysystem.entity.EducationScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationScopeRepository extends JpaRepository<EducationScope, Integer> {
}