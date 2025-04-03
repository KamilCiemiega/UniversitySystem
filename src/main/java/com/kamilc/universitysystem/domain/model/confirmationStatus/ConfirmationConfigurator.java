package com.kamilc.universitysystem.domain.model.confirmationStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmationConfigurator {
    @JsonProperty("first")
    private First first;

    @JsonProperty("second")
    private Second second;
}
