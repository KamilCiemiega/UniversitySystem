package com.kamilc.universitysystem.controller.dto.userDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUserResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
}
