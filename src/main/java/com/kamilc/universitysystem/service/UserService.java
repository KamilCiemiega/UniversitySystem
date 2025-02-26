package com.kamilc.universitysystem.service;

import com.kamilc.universitysystem.controller.dto.userDTOs.LoggedUserResponseDTO;
import com.kamilc.universitysystem.controller.dto.userDTOs.LoginUserDTO;
import com.kamilc.universitysystem.controller.dto.userDTOs.NewUserDTO;
import com.kamilc.universitysystem.controller.dto.userDTOs.RegisterUserResponseDTO;

public interface UserService {
    RegisterUserResponseDTO registerNewUser(NewUserDTO userDTO);
    LoggedUserResponseDTO loginUser(LoginUserDTO loginUserDTO);
}
