package com.kamilc.universitysystem.domain.model.scoringconfig;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoringConfiguration {
    @JsonProperty("scoring_rules")
    @NotNull(message = "ScoringRules must not be null")
    @Valid
    private ScoringRules scoringRules;

    @JsonProperty("field_of_study")
    @NotNull(message = "FieldConfig must not be null")
    @Valid
    private FieldConfig fieldConfig;
}
