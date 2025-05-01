package com.kamilc.universitysystem.web.dto.scoringdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MissingSubjectInfoDTO {
    String subjectName;

    @Override
    public String toString() {
        return "MissingSubjectInfoDTO{" +
                "subjectName='" + subjectName + '\'' +
                '}';
    }
}
