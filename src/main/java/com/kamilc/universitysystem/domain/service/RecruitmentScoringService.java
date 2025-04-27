package com.kamilc.universitysystem.domain.service;

import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationDraftDTO;
import com.kamilc.universitysystem.web.dto.scoringdtos.MissingSubjectInfoDTO;
import com.kamilc.universitysystem.web.dto.scoringdtos.ScoringResultExtendedDTO;
import com.kamilc.universitysystem.web.dto.scoringdtos.ScoringResultResponseDTO;

import java.util.List;
import java.util.Map;


public interface RecruitmentScoringService {
   ScoringResultExtendedDTO calculateScore(ApplicationConfigurator app, List<FieldOfStudy> fieldsOfStudy);
   Map<String, List<MissingSubjectInfoDTO>> verifyRequiredSubjects(
           ApplicationConfigurator app,
           List<FieldOfStudy> studies
   );
   ScoringResultExtendedDTO generateExtendedScoringResultDTO(List<FieldOfStudy> validStudies, ApplicationConfigurator app);
   List<ApplicationDraftDTO> generateScoreFromValidStudies(
           List<FieldOfStudy> validStudies,
           ApplicationConfigurator app
   );
}
