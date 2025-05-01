package com.kamilc.universitysystem.domain.service.helper;

import com.kamilc.universitysystem.domain.service.ApplicationService;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.mapper.ApplicationMapper;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationDraftDTO;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationResponseDTO;
import com.kamilc.universitysystem.web.dto.scoringdtos.ScoringResultExtendedDTO;
import com.kamilc.universitysystem.web.dto.scoringdtos.ScoringResultResponseDTO;
import com.kamilc.universitysystem.web.dto.userdtos.NewUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserServiceHelper {
    private final ApplicationMapper applicationMapper;
    private final ApplicationService applicationService;

    @Autowired
    public UserServiceHelper(ApplicationMapper applicationMapper, ApplicationService applicationService) {
        this.applicationMapper = applicationMapper;
        this.applicationService = applicationService;
    }

    public ScoringResultResponseDTO mapToBasicResult(ScoringResultExtendedDTO extendedDTO) {
        ScoringResultResponseDTO responseDTO = new ScoringResultResponseDTO();
        responseDTO.setApplicationResponseDTOs(extendedDTO.getApplicationResponseDTOs());
        responseDTO.setRejectedApplications(extendedDTO.getRejectedApplications());
        return responseDTO;
    }

    public List <ApplicationResponseDTO> mapAppToAppDTO(User savedUser) {
       return savedUser.getApplications()
               .stream()
               .map(applicationMapper::toApplicationResponseDTO)
               .toList();
    }

    public List<Application> generateApplicationsForUser(
            List<ApplicationDraftDTO> applicationDraftDTOs,
            NewUserDTO newUserDTO,
            User managedUser
    ){
         return applicationDraftDTOs
                .stream()
                .map(appDraft -> applicationService.createApplicationForUser(
                        appDraft,
                        managedUser,
                        newUserDTO.getApplicationData()
                ))
                .toList();
    }

}
