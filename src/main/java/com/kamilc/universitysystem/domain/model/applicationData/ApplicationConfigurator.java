package com.kamilc.universitysystem.domain.model.applicationData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationConfigurator {

    @JsonProperty("student_results")
    private List<StudentResult> studentResults;
}
