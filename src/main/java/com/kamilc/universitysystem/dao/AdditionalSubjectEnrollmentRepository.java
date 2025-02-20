package com.kamilc.universitysystem.dao;

import com.kamilc.universitysystem.entity.AdditionalSubjectEnrollment;
import com.kamilc.universitysystem.entity.AdditionalSubjectEnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalSubjectEnrollmentRepository extends JpaRepository<AdditionalSubjectEnrollment, AdditionalSubjectEnrollmentId> {}