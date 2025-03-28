package com.kamilc.universitysystem.mapper;

import com.kamilc.universitysystem.web.dto.userDtoS.NewUserDTO;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.web.dto.userDtoS.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EnumMapper.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", source = "role", qualifiedByName = "stringToEnum")
    User toEntity(NewUserDTO newUserDTO);

//    @Mapping(target = "id", source = "id")
//    UserResponseDTO toUserResponseDTO(User user);

    @Mapping(target = "role", source = "role", qualifiedByName = "enumToString")
    NewUserDTO toNewUserDTO(User user);
}