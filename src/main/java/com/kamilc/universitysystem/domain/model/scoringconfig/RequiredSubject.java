package com.kamilc.universitysystem.domain.model.scoringconfig;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequiredSubject {
    @NotBlank(message = "Subject name must not be blank")
    private String name;

    @NotNull(message = "Subject weight must not be null")
    private Double weight;

    @NotNull(message = "Field 'required' must be specified")
    private boolean required;
}
