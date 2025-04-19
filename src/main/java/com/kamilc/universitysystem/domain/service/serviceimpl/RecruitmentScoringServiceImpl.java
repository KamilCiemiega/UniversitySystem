package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.dao.ApplicationRepository;
import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.domain.model.scoringconfig.RequiredSubject;
import com.kamilc.universitysystem.domain.service.RecruitmentScoringService;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.web.dto.MissingSubjectInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class RecruitmentScoringServiceImpl implements RecruitmentScoringService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public RecruitmentScoringServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }


    @Override
    @Transactional
    public List<Application> calculateScore(ApplicationConfigurator app, List<FieldOfStudy> studies) {
        Map<String, List<MissingSubjectInfoDTO>> reqSubjects = verifyRequiredSubjects(app, studies);

         if(!reqSubjects.isEmpty()){
             throw new IllegalArgumentException("Missing required subjects: " + reqSubjects);
         }

        Application application = new Application();
        application.setStatus(Application.Status.PENDING);

        Application savedApp = applicationRepository.save(application);

        return List.of(savedApp);
    }

    public Map<String, List<MissingSubjectInfoDTO>> verifyRequiredSubjects(ApplicationConfigurator app, List<FieldOfStudy> studies) {
        Map<String, List<MissingSubjectInfoDTO>> missingSubjectsForStudies = new HashMap<>();

        Map<String, Set<String>> subjectLevels = new HashMap<>();
        app.getStudentResults()
                .forEach(studentResult ->
                        subjectLevels
                                .computeIfAbsent(studentResult.getSubject(), k -> new HashSet<>())
                                .add(studentResult.getLevel()));

        for (FieldOfStudy fS : studies) {
            for (RequiredSubject subject : fS.getScoringConfig().getFieldConfig().getRequiredSubjects()) {
                if (subject.isRequired()) {
                    String requiredName = subject.getName();
                    String requiredLevel = subject.getLevel();

                    Set<String> applicantLevels = subjectLevels.getOrDefault(requiredName, Set.of());

                    if (!applicantLevels.contains(requiredLevel)) {
//                        missingSubjectsForStudies
                                .computeIfAbsent(fS.getName(), k -> new ArrayList<>())
                                .add(new MissingSubjectInfoDTO(requiredName, requiredLevel));
                    }
                }
            }
        }

        return missingSubjectsForStudies;
    }
}
