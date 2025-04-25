package com.kamilc.universitysystem.domain.service.helper.userServiceHelpers;

import com.kamilc.universitysystem.domain.dao.FieldOfStudyRepository;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.web.dto.userdtos.UserResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserHelper {
    private final FieldOfStudyRepository fieldOfStudyRepository;

    @Autowired
    public UserHelper(FieldOfStudyRepository fieldOfStudyRepository) {
        this.fieldOfStudyRepository = fieldOfStudyRepository;
    }

    public List<FieldOfStudy> findFieldOfStudyEntity(List<Integer> fieldOfStudyIDs, User user){
        return fieldOfStudyIDs
                .stream()
                .map(studyId -> {
                    FieldOfStudy exFoS = fieldOfStudyRepository.findById(studyId)
                            .orElseThrow(() -> new EntityNotFoundException("Can't find field of study with ID: " + studyId));

                    user.getFieldsOfStudy().add(exFoS);

                    return exFoS;
                })
                .toList();
    }

    public UserResponseDTO setFieldsToUserResponseDTO(User savedUser, UserResponseDTO userResponseDTO) {
        Map<Integer, Application> fieldOfStudyAppMap = savedUser.getApplications()
                .stream()
                .collect(Collectors.toMap(
                        app -> app.getFieldOfStudy().getId(),
                        app -> app
                ));

        userResponseDTO.getScoringResult().getApplicationResponseDTOs()
                .forEach(appResponseDTO -> {
                    Application app = fieldOfStudyAppMap.get(appResponseDTO.getFieldOfStudyId());
                    if (app != null) {
                        appResponseDTO.setId(app.getId());
                        appResponseDTO.setAppliedAt(app.getAppliedAt());
                    } else {
                        log.info("Warning: No application found for fieldOfStudyId: {}", appResponseDTO.getFieldOfStudyId());
                    }
                });

        return userResponseDTO;
    }

}
