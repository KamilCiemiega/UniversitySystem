package com.kamilc.universitysystem.domain.service.serviceImpl;

import com.kamilc.universitysystem.web.dto.userDtoS.LoginUserDTO;
import com.kamilc.universitysystem.web.dto.userDtoS.NewUserDTO;
import com.kamilc.universitysystem.domain.dao.UserRepository;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.mapper.UserMapper;
import com.kamilc.universitysystem.domain.service.UserService;
import com.kamilc.universitysystem.web.dto.userDtoS.UserResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponseDTO register(NewUserDTO newUserDTO) {
        User user = userMapper.toEntity(newUserDTO);
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO login(LoginUserDTO loginUserDTO) {
        User user = userRepository.findByEmail(loginUserDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User with email " + loginUserDTO.getEmail() + " not found"));

        if (!passwordEncoder.matches(user.getPassword(), loginUserDTO.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return userMapper.toUserResponseDTO(user);
    }
}
