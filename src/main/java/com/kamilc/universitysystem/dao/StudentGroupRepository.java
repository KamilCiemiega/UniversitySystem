package com.kamilc.universitysystem.dao;

import com.kamilc.universitysystem.entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Integer> {
}