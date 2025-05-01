package com.kamilc.universitysystem.domain.model.scoringconfig;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class ScoringRules {
    @JsonProperty("level_multipliers")
    @NotEmpty(message = "Level multipliers cannot be empty")
    private Map<String, BigDecimal> levelMultipliers;
}
