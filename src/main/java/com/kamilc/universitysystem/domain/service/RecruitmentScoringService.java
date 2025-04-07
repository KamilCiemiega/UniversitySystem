package com.kamilc.universitysystem.domain.service;

import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.FieldOfStudy;

import java.util.List;


public interface RecruitmentScoringService {
    Application calculateScore( ApplicationConfigurator app, FieldOfStudy fieldOfStudy);
}
