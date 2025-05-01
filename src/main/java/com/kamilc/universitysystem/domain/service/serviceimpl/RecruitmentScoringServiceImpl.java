package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.domain.model.scoringconfig.RequiredSubject;
import com.kamilc.universitysystem.domain.service.RecruitmentScoringService;
import com.kamilc.universitysystem.domain.service.helper.scoringservicehelpers.ScoreCalculator;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationDraftDTO;
import com.kamilc.universitysystem.web.dto.scoringdtos.MissingSubjectInfoDTO;
import com.kamilc.universitysystem.web.dto.scoringdtos.ScoringResultExtendedDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecruitmentScoringServiceImpl implements RecruitmentScoringService {
    private final ScoreCalculator scoreCalculator;

    @Autowired
    public RecruitmentScoringServiceImpl(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
    }


    @Override
    public ScoringResultExtendedDTO calculateScore(ApplicationConfigurator app, List<FieldOfStudy> studies) {
        Map<String, List<MissingSubjectInfoDTO>> missingReqSubjects = verifyRequiredSubjects(app, studies);
        log.info("Field of study reqSubjects: {}", missingReqSubjects);

        Set<String> missingStudyNames = missingReqSubjects.keySet();

        List<FieldOfStudy> validStudies = studies.stream()
                .filter(fos -> !missingStudyNames.contains(fos.getName()))
                .toList();

        if (validStudies.isEmpty()) {
            throw new IllegalArgumentException("None of the selected fields of study meet the requirements. Missing subjects: " + missingReqSubjects);
        }

        ScoringResultExtendedDTO extendedResultDTO = generateExtendedScoringResultDTO(validStudies ,app);

        if (!missingReqSubjects.isEmpty()) {
            log.warn("Some fields of study were skipped due to missing required subjects: {}", missingReqSubjects);
            extendedResultDTO.setRejectedApplications(missingReqSubjects);
        }

        return extendedResultDTO;
    }

    @Override
    public Map<String, List<MissingSubjectInfoDTO>> verifyRequiredSubjects(ApplicationConfigurator app, List<FieldOfStudy> studies) {
        Map<String, Set<String>> subjectLevels = new HashMap<>();
        app.getStudentResults()
                .forEach(studentResult ->
                        subjectLevels
                                .computeIfAbsent(studentResult.getSubject(), k -> new HashSet<>())
                                .add(studentResult.getLevel()));

        log.info("student config: {}", subjectLevels);

        return studies.stream()
                .flatMap(fos -> fos.getScoringConfig().getFieldConfig().getRequiredSubjects().stream()
                        .filter(RequiredSubject::isRequired)
                        .filter(subj -> {
                            String name = subj.getName();
                            Set<String> applicantLevels = subjectLevels.getOrDefault(subj.getName(), Set.of());
                            return applicantLevels.isEmpty();
                        })
                        .map(subj -> Map.entry(fos.getName(), new MissingSubjectInfoDTO(subj.getName())))
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }

    @Override
    public ScoringResultExtendedDTO generateExtendedScoringResultDTO(List<FieldOfStudy> validStudies, ApplicationConfigurator app){
        ScoringResultExtendedDTO resultExtendedDTO = new ScoringResultExtendedDTO();
        resultExtendedDTO.setValidStudiesIDs(
                validStudies.stream()
                        .map(FieldOfStudy::getId)
                        .toList()
        );

        List<ApplicationDraftDTO> appDrafts = generateScoreForValidStudies(validStudies, app);
        resultExtendedDTO.setApplicationDraftDTOs(appDrafts);

        return resultExtendedDTO;
    }

    @Override
    public List<ApplicationDraftDTO> generateScoreForValidStudies(List<FieldOfStudy> validStudies, ApplicationConfigurator app){
        return validStudies.stream()
                .map(validStudy -> {
                    BigDecimal score = scoreCalculator.calculateScoreForFieldOfStudy(app, validStudy);
                    return new ApplicationDraftDTO(validStudy.getId(), score);
                })
                .toList();
    }

}
