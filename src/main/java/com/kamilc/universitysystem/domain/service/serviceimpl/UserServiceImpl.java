package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.dao.FieldOfStudyRepository;
import com.kamilc.universitysystem.domain.service.RecruitmentScoringService;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.web.dto.LoginUserDTO;
import com.kamilc.universitysystem.web.dto.userdtos.NewUserDTO;
import com.kamilc.universitysystem.domain.dao.UserRepository;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.mapper.UserMapper;
import com.kamilc.universitysystem.domain.service.UserService;
import com.kamilc.universitysystem.web.dto.userdtos.UserResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final FieldOfStudyRepository fieldOfStudyRepository;
    private final PasswordEncoder passwordEncoder;
    private final RecruitmentScoringService recruitmentScoringService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           UserRepository userRepository,
                           FieldOfStudyRepository fieldOfStudyRepository,
                           PasswordEncoder passwordEncoder,
                           RecruitmentScoringService recruitmentScoringService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.fieldOfStudyRepository = fieldOfStudyRepository;
        this.passwordEncoder = passwordEncoder;
        this.recruitmentScoringService = recruitmentScoringService;
    }

    @Override
    @Transactional
    public UserResponseDTO register(NewUserDTO newUserDTO) {
        User user = userMapper.toEntity(newUserDTO);
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));

        List <FieldOfStudy> fieldsOfStudy = newUserDTO.getFieldOfStudyIDs()
                .stream()
                        .map(studyId -> {
                           FieldOfStudy exFoS = fieldOfStudyRepository.findById(studyId)
                                    .orElseThrow(() -> new EntityNotFoundException("Can't find field of study entity with ID: " + studyId));

                           user.getFieldsOfStudy().add(exFoS);

                           return exFoS;
                        })
                        .toList();

       List <Application> applications = recruitmentScoringService
               .calculateScore(newUserDTO.getApplicationData(), fieldsOfStudy);

       User savedUser = userRepository.save(user);
       applications.forEach(app -> app.setUser(savedUser));
       savedUser.getApplications().addAll(applications);

       return userMapper.toUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO login(LoginUserDTO loginUserDTO) {
        User user = userRepository.findByEmail(loginUserDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User with email " + loginUserDTO.getEmail() + " not found"));

        if (!passwordEncoder.matches(loginUserDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return userMapper.toUserResponseDTO(user);
    }
}
