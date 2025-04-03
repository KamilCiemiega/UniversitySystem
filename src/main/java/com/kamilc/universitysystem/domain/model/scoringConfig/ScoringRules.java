package com.kamilc.universitysystem.domain.model.scoringConfig;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ScoringRules {

    @JsonProperty("level_multipliers")
    private Map<String, Double> levelMultipliers;

    @JsonProperty("confirmation_deadlines")
    private List<ConfirmationDeadline> confirmationDeadlines;
}
