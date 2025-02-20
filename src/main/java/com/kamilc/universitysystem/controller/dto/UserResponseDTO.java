package com.kamilc.universitysystem.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Integer id;
    private String email;
    private String name;
    private String surName;
    private String createdAt;
}
