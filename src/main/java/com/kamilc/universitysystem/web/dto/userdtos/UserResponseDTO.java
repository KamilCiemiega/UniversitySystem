package com.kamilc.universitysystem.web.dto.userdtos;

import com.kamilc.universitysystem.web.dto.ScoringResultDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Integer id;
    private LocalDateTime createdAt;
    private List <ScoringResultDTO> scoringResults;
}
