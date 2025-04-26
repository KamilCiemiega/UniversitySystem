package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.dao.ApplicationRepository;
import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.domain.service.ApplicationService;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.mapper.ApplicationMapper;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }


    @Override
    public Application save (
            ApplicationConfigurator appConfig,
            ApplicationResponseDTO appResponseDTO,
            User user) {

        Application app = applicationMapper.toApplication(appResponseDTO);
        app.setApplicationData(appConfig);
        app.setUser(user);

        return applicationRepository.save(app);
    }
}
