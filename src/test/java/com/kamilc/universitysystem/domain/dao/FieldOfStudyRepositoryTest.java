package com.kamilc.universitysystem.domain.dao;

import com.kamilc.universitysystem.domain.service.serviceimpl.TestDataFactory;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import(TestDataFactory.class)
class FieldOfStudyRepositoryTest {

    @Autowired
    private TestDataFactory testDataFactory;

    @Autowired
    private FieldOfStudyRepository fieldOfStudyRepository;

    @Test
    @DisplayName("Should find field of study by name")
    void shouldFindFieldOfStudyByName() {
        FieldOfStudy fos = new FieldOfStudy();
        fos.setName("Information Technology");
        fos.setStudyType(FieldOfStudy.StudyType.FULL_TIME);
        fos.setScoringConfig(testDataFactory.createITScoringConfig());

        fieldOfStudyRepository.save(fos);

        Optional<FieldOfStudy> found = fieldOfStudyRepository.findByName("Information Technology");

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Information Technology");
    }

    @Test
    @DisplayName("Should return empty when name does not exist")
    void shouldReturnEmptyIfFieldOfStudyNotFoundByName() {
        Optional<FieldOfStudy> result = fieldOfStudyRepository.findByName("Nonexistent");
        assertThat(result).isEmpty();
    }
}



