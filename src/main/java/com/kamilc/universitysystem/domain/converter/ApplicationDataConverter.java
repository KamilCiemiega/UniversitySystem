package com.kamilc.universitysystem.domain.converter;

import com.kamilc.universitysystem.domain.model.applicationData.ApplicationConfigurator;
import jakarta.persistence.Converter;

@Converter
public class ApplicationDataConverter extends GenericJsonConverter<ApplicationConfigurator> {
    public ApplicationDataConverter() {
        super(ApplicationConfigurator.class);
    }
}
