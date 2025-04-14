package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.dao.ApplicationRepository;
import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.domain.model.scoringconfig.RequiredSubject;
import com.kamilc.universitysystem.domain.service.RecruitmentScoringService;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        for (FieldOfStudy fS : studies) {
            List<String> requiredSubjectNames = fS.getScoringConfig()
                    .getFieldConfig()
                    .getRequiredSubjects()
                    .stream()
                    .filter(RequiredSubject::isRequired)
                    .map(RequiredSubject::getName)
                    .toList();

            List<String> studentSubjectNames = app.getStudentResults()
                    .stream()
                    .map(sR -> sR.getSubject().toLowerCase())
                    .toList();

            for (String requiredSubject : requiredSubjectNames) {
                if (studentSubjectNames.stream().noneMatch(s -> s.equalsIgnoreCase(requiredSubject))) {
                    throw new IllegalArgumentException("Missing required subject '" + requiredSubject + "' for field of study: " + fS.getName());
                }
            }
        }

        Application application = new Application();
        application.setStatus(Application.Status.PENDING);

        Application savedApp = applicationRepository.save(application);

        return List.of(savedApp);
    }
}
