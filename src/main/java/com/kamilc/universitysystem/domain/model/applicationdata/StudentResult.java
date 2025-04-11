package com.kamilc.universitysystem.domain.model.applicationdata;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResult {
    @NotBlank(message = "Subject field can't be empty")
    private String subject;

    @NotNull(message = "Level field can't be empty")
    private String level;

    @Min(value = 0, message = "Score can't be lover than 0")
    private int score;
}
