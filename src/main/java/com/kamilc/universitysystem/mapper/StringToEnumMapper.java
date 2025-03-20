package com.kamilc.universitysystem.mapper;

import jakarta.persistence.EnumType;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class StringToEnumMapper {
    @Named("stringToEnum")
    public <T extends Enum<T>> stringToEnum(String enumName){
        if (enumName != null){

        }
    }
}
