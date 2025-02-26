package com.kamilc.universitysystem.controller.dto.userDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponseDTO {
    private Integer id;
    private String email;
    private String name;
    private String surName;
    private String createdAt;
}
