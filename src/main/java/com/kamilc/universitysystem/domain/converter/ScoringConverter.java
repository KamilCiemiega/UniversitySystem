package com.kamilc.universitysystem.domain.converter;

import com.kamilc.universitysystem.domain.model.scoringConfig.ScoringConfiguration;
import jakarta.persistence.Converter;


@Converter
public class ScoringConverter extends GenericJsonConverter<ScoringConfiguration> {
    public ScoringConverter() {
        super(ScoringConfiguration.class);
    }
}
