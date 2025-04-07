package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.domain.service.RecruitmentScoringService;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentScoringServiceImpl implements RecruitmentScoringService {
    public RecruitmentScoringServiceImpl() {

    }

    @Override
    public Application calculateScore(ApplicationConfigurator app, FieldOfStudy fieldOfStudy) {
        return null;
    }
}
