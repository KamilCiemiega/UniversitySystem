package com.kamilc.universitysystem.web.dto.userdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.validation.UniqueValue;
import com.kamilc.universitysystem.web.dto.BasicUserDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@UniqueValue(
        entity = User.class,
        fieldName = "email",
        valueField = "email",
        message = "Email already exist"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDTO extends BasicUserDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "User role is required")
    private User.UserRole role;

    @NotNull(message = "Field of study ID can't be null")
    private List<Integer> fieldOfStudyIDs = new ArrayList<>();

    @Valid
    @NotNull(message = "Application data can't be empty")
    @JsonProperty("application_data")
    private ApplicationConfigurator applicationData;
}
