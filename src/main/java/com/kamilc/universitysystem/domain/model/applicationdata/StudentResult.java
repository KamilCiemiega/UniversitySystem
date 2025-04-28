package com.kamilc.universitysystem.domain.model.applicationdata;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResult {
    @NotBlank(message = "Subject field can't be empty")
    private String subject;

    @NotNull(message = "Level field can't be empty")
    private String level;

    @DecimalMin(value = "0.00", message = "Score can't be lover than 0")
    private BigDecimal score;
}