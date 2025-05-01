package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.dao.FieldOfStudyRepository;
import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.entity.Application;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.entity.User;
import com.kamilc.universitysystem.web.dto.applicationdtos.ApplicationDraftDTO;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {

    @Mock
    private FieldOfStudyRepository fieldOfStudyRepository;

    @InjectMocks
    private ApplicationServiceImpl underTest;

    @DisplayName("should create Application for user with correct values")
    @Test
    void shouldCreateApplicationForUserCorrectly() {
        // Arrange
        ApplicationDraftDTO appDraft = new ApplicationDraftDTO();
        appDraft.setFieldOfStudyId(1);
        appDraft.setScore(new BigDecimal("85.00"));

        User user = new User();
        user.setId(1);

        ApplicationConfigurator appConfig = new ApplicationConfigurator();

        FieldOfStudy fieldOfStudy = new FieldOfStudy();
        fieldOfStudy.setId(1);
        fieldOfStudy.setName("Test Field");

        when(fieldOfStudyRepository.findById(1)).thenReturn(Optional.of(fieldOfStudy));

        // Act
        Application application = underTest.createApplicationForUser(appDraft, user, appConfig);

        // Assert
        assertNotNull(application);
        assertEquals(user, application.getUser());
        assertEquals(appConfig, application.getApplicationData());
        assertEquals(appDraft.getScore(), application.getScore());
        assertEquals(fieldOfStudy, application.getFieldOfStudy());
        assertEquals(Application.Status.PENDING, application.getStatus());
    }

    @DisplayName("Should throw EntityNotFoundException when field of study is not found")
    @Test
    void shouldThrowWhenFieldOfStudyNotFound() {
        ApplicationDraftDTO appDraft = new ApplicationDraftDTO();
        appDraft.setFieldOfStudyId(99);

        when(fieldOfStudyRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                underTest.createApplicationForUser(appDraft, new User(), new ApplicationConfigurator())
        );
    }
}