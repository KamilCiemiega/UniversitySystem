package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.dao.ApplicationRepository;
import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.domain.model.scoringconfig.RequiredSubject;
import com.kamilc.universitysystem.domain.service.RecruitmentScoringService;
import com.kamilc.universitysystem.domain.service.helper.scoringServiceHelpers.ScoreCalculator;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.mapper.ApplicationMapper;
import com.kamilc.universitysystem.web.dto.scoringdtos.MissingSubjectInfoDTO;
import com.kamilc.universitysystem.web.dto.scoringdtos.ScoringResultDTO;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecruitmentScoringServiceImpl implements RecruitmentScoringService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final ScoreCalculator scoreCalculator;

    @Autowired
    public RecruitmentScoringServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper, ScoreCalculator scoreCalculator) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.scoreCalculator = scoreCalculator;
    }


    @Override
    @Transactional
    public ScoringResultDTO calculateScore(ApplicationConfigurator app, List<FieldOfStudy> studies) {
        Map<String, List<MissingSubjectInfoDTO>> missingReqSubjects = verifyRequiredSubjects(app, studies);
        log.info("Field of study reqSubjects: {}", missingReqSubjects);

        Set<String> missingStudyNames = missingReqSubjects.keySet();

        List<FieldOfStudy> validStudies = studies.stream()
                .filter(fos -> !missingStudyNames.contains(fos.getName()))
                .toList();

        if (validStudies.isEmpty()) {
            throw new IllegalArgumentException("None of the selected fields of study meet the requirements. Missing subjects: " + missingReqSubjects);
        }

        ScoringResultDTO scoringResultDTO = new ScoringResultDTO();

        List<ApplicationResponseDTO> savedApplicationsDTOs = generateApplicationsFromValidStudies(validStudies, app);

        scoringResultDTO.setApplicationResponseDTOs(savedApplicationsDTOs);

        if (!missingReqSubjects.isEmpty()) {
            log.warn("Some fields of study were skipped due to missing required subjects: {}", missingReqSubjects);
            scoringResultDTO.setRejectedApplications(missingReqSubjects);
        }

        return scoringResultDTO;
    }

    public Map<String, List<MissingSubjectInfoDTO>> verifyRequiredSubjects(ApplicationConfigurator app, List<FieldOfStudy> studies) {
        //English: [basic, extended]
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
                            String level = subj.getLevel();
                            Set<String> applicantLevels = subjectLevels.getOrDefault(name, Set.of());
                            return !applicantLevels.contains(level);
                        })
                        .map(subj -> Map.entry(fos.getName(), new MissingSubjectInfoDTO(subj.getName(), subj.getLevel())))
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }

    public List<ApplicationResponseDTO> generateApplicationsFromValidStudies(List<FieldOfStudy> validStudies, ApplicationConfigurator app) {
        return validStudies.stream()
                .map(validStudy -> {
                    BigDecimal score = scoreCalculator.calculateScoreForFieldOfStudy(app, validStudy);

                    ApplicationResponseDTO applicationResponseDTO = new ApplicationResponseDTO();
                    applicationResponseDTO.setScore(score);
                    applicationResponseDTO.setStatus(Application.Status.PENDING);
                    applicationResponseDTO.setFieldOfStudyId(validStudy.getId());

                    return  applicationResponseDTO;
                })
                .toList();
    }

}
