package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.service.FieldOfStudyService;
import com.kamilc.universitysystem.domain.service.RecruitmentScoringService;
import com.kamilc.universitysystem.domain.service.helper.UserServiceHelper;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.web.dto.LoginUserDTO;
import com.kamilc.universitysystem.web.dto.scoringdtos.ScoringResultExtendedDTO;
import com.kamilc.universitysystem.web.dto.scoringdtos.ScoringResultResponseDTO;
import com.kamilc.universitysystem.web.dto.userdtos.NewUserDTO;
import com.kamilc.universitysystem.domain.dao.UserRepository;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.mapper.UserMapper;
import com.kamilc.universitysystem.domain.service.UserService;
import com.kamilc.universitysystem.web.dto.userdtos.UserResponseDTO;
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
    private final PasswordEncoder passwordEncoder;
    private final RecruitmentScoringService recruitmentScoringService;
    private final UserServiceHelper userServiceHelper;
    private final FieldOfStudyService fieldOfStudyService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RecruitmentScoringService recruitmentScoringService,
                           UserServiceHelper userServiceHelper,
                           FieldOfStudyService fieldOfStudyService
    ){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.recruitmentScoringService = recruitmentScoringService;
        this.userServiceHelper = userServiceHelper;
        this.fieldOfStudyService = fieldOfStudyService;
    }

    @Override
    @Transactional
    public UserResponseDTO register(NewUserDTO newUserDTO) {
        User user = userMapper.toEntity(newUserDTO);
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));

       ScoringResultExtendedDTO extendedScoringResultDTO = recruitmentScoringService
               .calculateScore(newUserDTO.getApplicationData(),
                       fieldOfStudyService.findFieldOfStudy(newUserDTO.getFieldOfStudyIDs(),user)
               );

        User managedUser = userRepository.save(user);

        List<Application> applicationList = userServiceHelper.generateApplicationsForUser(
                extendedScoringResultDTO.getApplicationDraftDTOs(),
                newUserDTO,
                managedUser
        );

        managedUser.getApplications().addAll(applicationList);

       User savedUser = userRepository.save(managedUser);

       UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(savedUser);
       ScoringResultResponseDTO scoringResultResponseDTO = userServiceHelper.mapToBasicResult(extendedScoringResultDTO);
       scoringResultResponseDTO.setApplicationResponseDTOs(userServiceHelper.mapAppToAppDTO(savedUser));

       userResponseDTO.setScoringResult(scoringResultResponseDTO);

       return userResponseDTO;
    }

    @Override
    public UserResponseDTO login(LoginUserDTO loginUserDTO) {
        User user = userRepository.findByEmail(loginUserDTO.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(loginUserDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return userMapper.toUserResponseDTO(user);
    }


}
