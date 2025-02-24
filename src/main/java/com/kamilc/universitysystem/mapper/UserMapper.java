package com.kamilc.universitysystem.mapper;

import com.kamilc.universitysystem.controller.dto.NewUserDTO;
import com.kamilc.universitysystem.controller.dto.UserResponseDTO;
import com.kamilc.universitysystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", source = "role", qualifiedByName = "mapRole")
    User toEntity(NewUserDTO newUserDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateTimeToString")
    UserResponseDTO toDTO(User user);

    @Named("localDateTimeToString")
    static String localDateTimeToString(LocalDateTime createdAt) {
        return createdAt != null ? createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null;
    }

    @Named("mapRole")
    default User.UserRole mapRole(String role) {
        return role != null ? User.UserRole.valueOf(role.toUpperCase()) : null;
    }
}