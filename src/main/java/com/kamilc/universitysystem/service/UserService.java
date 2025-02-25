package com.kamilc.universitysystem.service;

import com.kamilc.universitysystem.controller.dto.NewUserDTO;
import com.kamilc.universitysystem.controller.dto.UserResponseDTO;
import com.kamilc.universitysystem.entity.User;

public interface UserService {
    UserResponseDTO saveNewUser(NewUserDTO userDTO);
}
