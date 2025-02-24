package com.kamilc.universitysystem.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDTO {
    private String email;
    private String password;
    private String name;
    private String surName;
    private String role;
}
