package com.kamilc.universitysystem.web.dto.applicationdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    @Valid
    @NotNull
    @JsonProperty("application_data")
    private ApplicationConfigurator applicationData;

    @NotNull(message = "Study id can't be null")
    private Integer fieldOfStudyId;
}
