package com.kamilc.universitysystem.web.dto;

import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.validation.UniqueValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@UniqueValue(
        entity = FieldOfStudy.class,
        fieldName = "name",
        valueField = "name",
        message = "Name field isn't unique"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldOfStudyApplicationDTO {
    @NotBlank(message = "Name field can't be empty")
    private String name;

    @NotNull(message = "Semester ID can't be null")
    private Integer semesterId;

    @NotBlank(message = "Study type can't be empty")
    private String studyType;
}
