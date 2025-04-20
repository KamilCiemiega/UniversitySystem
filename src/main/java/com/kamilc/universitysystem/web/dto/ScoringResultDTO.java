package com.kamilc.universitysystem.web.dto;


import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoringResultDTO {
    private List<ApplicationResponseDTO> applicationResponseDTOs = new ArrayList<>();
    private Map<String, List<MissingSubjectInfoDTO>> rejectedApplications = new HashMap<>();
}
