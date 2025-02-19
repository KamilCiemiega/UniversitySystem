package com.kamilc.universitysystem.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private String email;
    private String name;
    private String surName;
    private String createdAt;
}
