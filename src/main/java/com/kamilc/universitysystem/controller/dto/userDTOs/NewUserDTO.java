package com.kamilc.universitysystem.controller.dto.userDTOs;

import com.kamilc.universitysystem.controller.dto.FieldOfStudyDTO;
import com.kamilc.universitysystem.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class NewUserDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Surname is required")
    private String surName;

    @NotBlank(message = "User role is required")
    private User.UserRole role;

    private List<FieldOfStudyDTO> fieldOfStudyDTOS = new ArrayList<>();
    private Integer groupID;
}
