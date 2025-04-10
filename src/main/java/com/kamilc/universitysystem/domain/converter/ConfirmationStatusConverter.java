package com.kamilc.universitysystem.domain.converter;

import com.kamilc.universitysystem.domain.model.confirmationstatus.ConfirmationConfigurator;
import jakarta.persistence.Converter;

@Converter
public class ConfirmationStatusConverter extends GenericJsonConverter<ConfirmationConfigurator> {
    public ConfirmationStatusConverter() {
        super(ConfirmationConfigurator.class);
    }
}
