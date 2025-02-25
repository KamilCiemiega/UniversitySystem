package com.kamilc.universitysystem.service.serviceImpl;

import com.kamilc.universitysystem.controller.dto.NewUserDTO;
import com.kamilc.universitysystem.controller.dto.UserResponseDTO;
import com.kamilc.universitysystem.dao.UserRepository;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.mapper.UserMapper;
import com.kamilc.universitysystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserResponseDTO saveNewUser(NewUserDTO userDTO) {
        return null;
    }

//    @Override
//    @Transactional
//    public UserResponseDTO saveNewUser(NewUserDTO userDTO) {
//
//        User user = userMapper.toEntity(userDTO);
//        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
//
//
//
////        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
////        user.setPassword(encodedPassword);
////
////        return userRepository.save(user);
//
//        return new
//    }





}
