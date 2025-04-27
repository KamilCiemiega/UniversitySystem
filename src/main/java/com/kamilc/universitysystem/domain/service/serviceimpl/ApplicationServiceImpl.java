package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.dao.FieldOfStudyRepository;
import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.domain.service.ApplicationService;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationDraftDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final FieldOfStudyRepository fieldOfStudyRepository;


    public ApplicationServiceImpl(FieldOfStudyRepository fieldOfStudyRepository) {
        this.fieldOfStudyRepository = fieldOfStudyRepository;
    }

    @Override
    public Application createApplicationForUser(ApplicationDraftDTO appDraft, User managedUser, ApplicationConfigurator appConfig) {
        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findById(appDraft.getFieldOfStudyId())
                .orElseThrow(() -> new EntityNotFoundException("Field of Study with ID " + appDraft.getFieldOfStudyId() + " not found"));

        Application newApp = new Application();
        newApp.setUser(managedUser);
        newApp.setApplicationData(appConfig);
        newApp.setScore(appDraft.getScore());
        newApp.setFieldOfStudy(fieldOfStudy);
        newApp.setStatus(Application.Status.PENDING);

        return newApp;
    }
}
