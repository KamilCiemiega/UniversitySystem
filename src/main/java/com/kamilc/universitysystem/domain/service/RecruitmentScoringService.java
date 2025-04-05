package com.kamilc.universitysystem.domain.service;

import com.kamilc.universitysystem.entity.Application;

public interface RecruitmentScoringService {
    Application calculateScore(Application app);
}
