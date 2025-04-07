package com.kamilc.universitysystem.web.dto.userdtos;

import com.kamilc.universitysystem.web.dto.BasicUserDTO;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotNull(message = "Application data is required")
    private List<ApplicationDTO> application;
}
