package com.kamilc.universitysystem.mapper;

import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    @Mapping(source = "fieldOfStudy.id", target = "fieldOfStudyId")
    ApplicationResponseDTO toApplicationResponseDTO(Application app);

    Application toApplication(ApplicationResponseDTO appDTO);
}
