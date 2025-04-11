package com.kamilc.universitysystem.web.dto.userdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.web.dto.BasicUserDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @NotBlank(message = "User role is required")
    private String role;

    @Valid
    @NotEmpty(message = "Application data can't be empty")
    @JsonProperty("application_data")
    private List<ApplicationConfigurator> applicationData = new ArrayList<>();


}
