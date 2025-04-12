package com.kamilc.universitysystem.mapper;

import com.kamilc.universitysystem.web.dto.userdtos.NewUserDTO;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.web.dto.userdtos.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EnumMapper.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", source = "role", qualifiedByName = "stringToEnum")
    User toEntity(NewUserDTO newUserDTO);

    UserResponseDTO toUserResponseDTO(User user);

    @Mapping(target = "role", source = "role", qualifiedByName = "enumToString")
    NewUserDTO toNewUserDTO(User user);
}