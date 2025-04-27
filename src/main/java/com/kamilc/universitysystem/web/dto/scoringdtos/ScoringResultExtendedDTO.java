package com.kamilc.universitysystem.web.dto.scoringdtos;

import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationDraftDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoringResultExtendedDTO extends ScoringResultResponseDTO {
    private List<Integer> validStudiesIDs = new ArrayList<>();
    private List<ApplicationDraftDTO> applicationDraftDTOs = new ArrayList<>();
}
