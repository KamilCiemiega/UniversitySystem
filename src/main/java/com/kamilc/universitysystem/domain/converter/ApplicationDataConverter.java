package com.kamilc.universitysystem.domain.converter;

import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import jakarta.persistence.Converter;

@Converter
public class ApplicationDataConverter extends GenericJsonConverter<ApplicationConfigurator> {
    public ApplicationDataConverter() {
        super(ApplicationConfigurator.class);
    }
}
