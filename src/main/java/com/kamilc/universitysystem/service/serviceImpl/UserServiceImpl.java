package com.kamilc.universitysystem.service.serviceImpl;

import com.kamilc.universitysystem.controller.dto.userDTOs.LoggedUserResponseDTO;
import com.kamilc.universitysystem.controller.dto.userDTOs.LoginUserDTO;
import com.kamilc.universitysystem.controller.dto.userDTOs.NewUserDTO;
import com.kamilc.universitysystem.controller.dto.userDTOs.RegisterUserResponseDTO;
import com.kamilc.universitysystem.dao.UserRepository;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.mapper.UserMapper;
import com.kamilc.universitysystem.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //
//    @Autowired
//    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userMapper = userMapper;
//        this.userRepository = userRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }

    @Override
    @Transactional
    public RegisterUserResponseDTO registerNewUser(NewUserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public LoggedUserResponseDTO loginUser(LoginUserDTO loginUserDTO) {
        User user = userRepository.findByEmail(loginUserDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User with email " + loginUserDTO.getEmail() + " not found"));

        if (!bCryptPasswordEncoder.matches(loginUserDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return null;
    }
}
