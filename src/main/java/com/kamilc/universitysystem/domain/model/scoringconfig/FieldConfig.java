package com.kamilc.universitysystem.domain.model.scoringconfig;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FieldConfig {
    private String name;
    private String code;
    private int minScore;
    private int availableSlots;

    @JsonProperty("required_subjects")
    private List<RequiredSubject> requiredSubjects;
}
