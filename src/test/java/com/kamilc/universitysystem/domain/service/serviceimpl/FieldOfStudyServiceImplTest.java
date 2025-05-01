package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.dao.FieldOfStudyRepository;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FieldOfStudyServiceImplTest {

    @Mock
    private FieldOfStudyRepository fieldOfStudyRepository;

    @InjectMocks
    private FieldOfStudyServiceImpl underTest;

    @Test
    @DisplayName("Should return correct list of FieldOfStudy and update user's fields")
    void shouldReturnFieldOfStudiesAndUpdateUserFields() {
        // Arrange
        FieldOfStudy field1 = new FieldOfStudy();
        field1.setId(1);
        field1.setName("IT");

        FieldOfStudy field2 = new FieldOfStudy();
        field2.setId(2);
        field2.setName("Robotic");

        User user = new User();
        user.setFieldsOfStudy(new ArrayList<>());

        when(fieldOfStudyRepository.findById(1)).thenReturn(Optional.of(field1));
        when(fieldOfStudyRepository.findById(2)).thenReturn(Optional.of(field2));

        List<Integer> ids = List.of(1, 2);

        // Act
        List<FieldOfStudy> result = underTest.findFieldOfStudy(ids, user);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(field1));
        assertTrue(result.contains(field2));
        assertEquals(2, user.getFieldsOfStudy().size());
    }

    @Test
    @DisplayName("Should throw exception if any FieldOfStudy not found")
    void shouldThrowWhenFieldOfStudyNotFound() {
        // Arrange
        User user = new User();
        user.setFieldsOfStudy(List.of());

        when(fieldOfStudyRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () ->
                underTest.findFieldOfStudy(List.of(99), user));

        assertEquals("Can't find field of study with ID: 99", thrown.getMessage());
    }

}