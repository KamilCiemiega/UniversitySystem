package com.kamilc.universitysystem.mapper;

import com.kamilc.universitysystem.controller.dto.JwtKeyDTO;
import com.kamilc.universitysystem.entity.JwtKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = LocalDateTimeMapper.class)
public interface JwtKeyMapper{
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateTimeToString")
    JwtKeyDTO toDto(JwtKey entity);

}
