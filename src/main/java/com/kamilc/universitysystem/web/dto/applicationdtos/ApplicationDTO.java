package com.kamilc.universitysystem.web.dto.applicationdtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {

    @NotNull(message = "Study id can't be null")
    private Integer fieldOfStudyId;
}
