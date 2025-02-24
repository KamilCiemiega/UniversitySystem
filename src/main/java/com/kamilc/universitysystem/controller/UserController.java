package com.kamilc.universitysystem.controller;

import com.kamilc.universitysystem.controller.dto.NewUserDTO;
import com.kamilc.universitysystem.controller.dto.UserResponseDTO;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.mapper.UserMapper;
import com.kamilc.universitysystem.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO saveNewUser(@RequestBody @Valid NewUserDTO newUserDTO) {
        User user = userMapper.toEntity(newUserDTO);
        User savedUser = userService.save(user);
        logger.info("User with email: {} has been registered", savedUser.getEmail());

        return userMapper.toDTO(savedUser);
    }
}
