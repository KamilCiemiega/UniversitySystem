package com.kamilc.universitysystem.domain.service;

import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.web.dto.MissingSubjectInfoDTO;
import com.kamilc.universitysystem.web.dto.ScoringResultDTO;

import java.util.List;
import java.util.Map;


public interface RecruitmentScoringService {
   ScoringResultDTO calculateScore(ApplicationConfigurator app, List<FieldOfStudy> fieldsOfStudy);
   Map<String, List<MissingSubjectInfoDTO>> verifyRequiredSubjects(ApplicationConfigurator app, List<FieldOfStudy> studies);
}
