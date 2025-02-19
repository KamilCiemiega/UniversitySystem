package com.kamilc.universitysystem.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String role;
}
