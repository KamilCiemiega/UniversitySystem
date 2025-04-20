package com.kamilc.universitysystem.mapper;

import com.kamilc.universitysystem.web.dto.userdtos.NewUserDTO;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.web.dto.userdtos.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(NewUserDTO newUserDTO);

    UserResponseDTO toUserResponseDTO(User user);

    NewUserDTO toNewUserDTO(User user);
}