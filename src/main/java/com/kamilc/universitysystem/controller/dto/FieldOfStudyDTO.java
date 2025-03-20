package com.kamilc.universitysystem.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldOfStudyDTO {
    private Integer id;
    private String name;
    private Integer semesterId;

//    private List<SemesterDTO> semesterDTOS = new ArrayList<>();
}
