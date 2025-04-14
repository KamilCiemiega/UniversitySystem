package com.kamilc.universitysystem.domain.model.scoringconfig;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FieldConfig {
    @NotBlank(message = "Field code must not be blank")
    private String code;

    @JsonProperty("required_subjects")
    @NotEmpty(message = "Required subjects list cannot be empty")
    private List<RequiredSubject> requiredSubjects;
}
