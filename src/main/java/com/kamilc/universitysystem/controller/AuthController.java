package com.kamilc.universitysystem.controller;


import com.kamilc.universitysystem.controller.dto.NewUserDTO;
import com.kamilc.universitysystem.controller.dto.UserResponseDTO;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.mapper.UserMapper;
import com.kamilc.universitysystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final UserService userService;

    public AuthController(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper, UserService userService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> saveNewUser(@RequestBody NewUserDTO newUserDTO){
        User user = userMapper.toEntity(newUserDTO);
        User savedUser = userService.save(user);
        UserResponseDTO savedUserDTO = userMapper.toDTO(savedUser);

        return savedUserDTO;
    }



}
