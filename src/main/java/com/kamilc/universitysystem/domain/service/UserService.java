package com.kamilc.universitysystem.domain.service;

import com.kamilc.universitysystem.web.dto.userDtoS.LoginUserDTO;
import com.kamilc.universitysystem.web.dto.userDtoS.NewUserDTO;
import com.kamilc.universitysystem.web.dto.userDtoS.UserResponseDTO;

public interface UserService {
    UserResponseDTO register(NewUserDTO newUserDTO);
    UserResponseDTO login(LoginUserDTO loginUserDTO);
}
