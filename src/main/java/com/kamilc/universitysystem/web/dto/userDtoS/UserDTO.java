package com.kamilc.universitysystem.web.dto.userDtoS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BasicUserDTO {
    private Integer id;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private String role;
}
