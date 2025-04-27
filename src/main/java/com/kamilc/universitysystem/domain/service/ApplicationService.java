package com.kamilc.universitysystem.domain.service;


import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationDraftDTO;

public interface ApplicationService {
    Application createApplicationForUser(
            ApplicationDraftDTO appDraft,
            User managedUser,
            ApplicationConfigurator appConfig
    );
}
