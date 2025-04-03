package com.kamilc.universitysystem.domain.model.scoringConfig;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConfirmationDeadline {
    private String stage;
    private LocalDate deadline;
}
