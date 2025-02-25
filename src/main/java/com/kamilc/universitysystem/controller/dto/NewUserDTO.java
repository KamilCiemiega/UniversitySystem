package com.kamilc.universitysystem.controller.dto;

import com.kamilc.universitysystem.entity.User;
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
    private String email;
    private String password;
    private String name;
    private String surName;
    private User.UserRole role;
    private List<FieldOfStudyDTO> fieldOfStudyDTOS = new ArrayList<>();
    private Integer groupID;
}
