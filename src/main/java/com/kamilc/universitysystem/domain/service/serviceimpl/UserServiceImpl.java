package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.dao.FieldOfStudyRepository;
import com.kamilc.universitysystem.domain.service.ApplicationService;
import com.kamilc.universitysystem.domain.service.RecruitmentScoringService;
import com.kamilc.universitysystem.domain.service.helper.userServiceHelpers.UserHelper;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.mapper.ApplicationMapper;
import com.kamilc.universitysystem.web.dto.LoginUserDTO;
import com.kamilc.universitysystem.web.dto.scoringdtos.ScoringResultDTO;
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


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final ApplicationMapper applicationMapper;
    private final UserRepository userRepository;
    private final FieldOfStudyRepository fieldOfStudyRepository;
    private final PasswordEncoder passwordEncoder;
    private final RecruitmentScoringService recruitmentScoringService;
    private final UserHelper userHelper;
    private final ApplicationService applicationService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, ApplicationMapper applicationMapper,
                           UserRepository userRepository,
                           FieldOfStudyRepository fieldOfStudyRepository,
                           PasswordEncoder passwordEncoder,
                           RecruitmentScoringService recruitmentScoringService, UserHelper userHelper, ApplicationService applicationService) {
        this.userMapper = userMapper;
        this.applicationMapper = applicationMapper;
        this.userRepository = userRepository;
        this.fieldOfStudyRepository = fieldOfStudyRepository;
        this.passwordEncoder = passwordEncoder;
        this.recruitmentScoringService = recruitmentScoringService;
        this.userHelper = userHelper;
        this.applicationService = applicationService;
    }

    @Override
    @Transactional
    public UserResponseDTO register(NewUserDTO newUserDTO) {
        User user = userMapper.toEntity(newUserDTO);
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));

       ScoringResultDTO scoringResultDTO = recruitmentScoringService
               .calculateScore(newUserDTO.getApplicationData(),
                       userHelper.findFieldOfStudyEntity(newUserDTO.getFieldOfStudyIDs(),user)
               );

        User savedUser = userRepository.save(user);

        scoringResultDTO.getApplicationResponseDTOs().forEach(appDTO -> {
           Application app = applicationService.save(newUserDTO.getApplicationData(), appDTO, savedUser);
           savedUser.getApplications().add(app);
       });

        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(savedUser);

        userResponseDTO.setScoringResult(scoringResultDTO);

        return userResponseDTO;
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
