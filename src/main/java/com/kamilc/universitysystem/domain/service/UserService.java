package com.kamilc.universitysystem.domain.service;

import com.kamilc.universitysystem.web.dto.LoginUserDTO;
import com.kamilc.universitysystem.web.dto.userdtos.NewUserDTO;
import com.kamilc.universitysystem.web.dto.userdtos.UserResponseDTO;

public interface UserService {
    UserResponseDTO register(NewUserDTO newUserDTO);
    UserResponseDTO login(LoginUserDTO loginUserDTO);
}
