package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.dao.ApplicationRepository;
import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.domain.service.RecruitmentScoringService;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentScoringServiceImpl implements RecruitmentScoringService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public RecruitmentScoringServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<Application> calculateScore(ApplicationConfigurator app, List<FieldOfStudy> studies) {
        Application application = new Application();
        application.setStatus(Application.Status.PENDING);

        Application savedApp = applicationRepository.save(application);

        return List.of(savedApp);
    }
}
