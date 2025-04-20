package com.kamilc.universitysystem.web.dto.applicationdtos;

import com.kamilc.universitysystem.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDTO {
    private Integer id;
    private Application.Status status;
    private BigDecimal score;
    private LocalDateTime appliedAt;
    private Integer fieldOfStudyId;
}
