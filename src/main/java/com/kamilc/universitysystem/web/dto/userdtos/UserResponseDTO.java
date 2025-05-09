package com.kamilc.universitysystem.web.dto.userdtos;

import com.kamilc.universitysystem.web.dto.scoringdtos.ScoringResultResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Integer id;
    private LocalDateTime createdAt;
    private ScoringResultResponseDTO scoringResult;
}
