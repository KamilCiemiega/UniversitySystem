package com.kamilc.universitysystem.mapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateTimeMapper {
    @Named("localDateTimeToString")
    public String localDateTimeToString(LocalDateTime createdAt) {
        return createdAt != null ? createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null;
    }
}
