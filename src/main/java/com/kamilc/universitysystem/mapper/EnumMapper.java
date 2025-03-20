package com.kamilc.universitysystem.mapper;

import com.kamilc.universitysystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface EnumMapper {

    @Named("enumToString")
    default <T extends Enum<T>> String enumToString(T enumValue) {
        return enumValue != null ? enumValue.name() : null;
    }

    @Named("stringToEnum")
    default User.UserRole stringToEnum(String value) {
        if (value == null) {
            return null;
        }
        try {
            return User.UserRole.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid enum value: " + value);
        }
    }
}
