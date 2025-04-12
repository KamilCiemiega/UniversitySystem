package com.kamilc.universitysystem.mapper;

import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EnumMapper.class)
public interface ApplicationMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "status", source = "status", qualifiedByName = "enumToString")
    ApplicationResponseDTO toApplicationResponseDTO(Application app);
}
