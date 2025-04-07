package com.kamilc.universitysystem.domain.model.scoringconfig;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoringConfiguration {
    @JsonProperty("scoring_rules")
    private ScoringRules scoringRules;

    @JsonProperty("field_of_study")
    private FieldConfig fieldConfig;
}
