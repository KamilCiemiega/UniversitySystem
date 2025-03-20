package com.kamilc.universitysystem.web.dto.userDtoS;

import com.kamilc.universitysystem.web.dto.FieldOfStudyDTO;
import com.kamilc.universitysystem.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class NewUserDTO extends BasicUserDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "User role is required")
    private String role;

    private List<FieldOfStudyDTO> fieldOfStudyDTOs = new ArrayList<>();
    private Integer groupId;
}
