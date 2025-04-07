package com.kamilc.universitysystem.web.dto.userdtos;

import com.kamilc.universitysystem.web.dto.BasicUserDTO;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO extends BasicUserDTO {
    private Integer id;
    private ApplicationResponseDTO applicationResponse;
}
