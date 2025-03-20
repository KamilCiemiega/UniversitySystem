package com.kamilc.universitysystem.mapper;

import com.kamilc.universitysystem.controller.dto.userDTOs.NewUserDTO;
import com.kamilc.universitysystem.controller.dto.userDTOs.RegisterUserResponseDTO;
import com.kamilc.universitysystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = LocalDateTimeMapper.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(NewUserDTO newUserDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateTimeToString")
    RegisterUserResponseDTO toDTO(User user);
}