package com.kamilc.universitysystem.web.dto.applicationdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDraftDTO {
    private Integer fieldOfStudyId;
    private BigDecimal score;
}
