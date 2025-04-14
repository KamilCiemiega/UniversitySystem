package com.kamilc.universitysystem.domain.model.applicationdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationConfigurator {
    @JsonProperty("student_results")
    @Valid
    private List<StudentResult> studentResults;
}
