package com.kamilc.universitysystem.domain.service;

import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.entity.User;

import java.util.List;

public interface FieldOfStudyService {
    List<FieldOfStudy> findFieldOfStudy(List<Integer> fieldOfStudyIDs, User user);
}
