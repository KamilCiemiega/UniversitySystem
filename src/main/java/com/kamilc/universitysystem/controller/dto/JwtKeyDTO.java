package com.kamilc.universitysystem.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtKeyDTO {
    private Integer id;
    private String createdAt;
    private Boolean isActive;
}
