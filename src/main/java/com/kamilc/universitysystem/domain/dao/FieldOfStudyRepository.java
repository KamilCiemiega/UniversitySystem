package com.kamilc.universitysystem.domain.dao;

import com.kamilc.universitysystem.entity.FieldOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldOfStudyRepository extends JpaRepository<FieldOfStudy, Integer> {
        Optional<FieldOfStudy> findByName(String name);
}