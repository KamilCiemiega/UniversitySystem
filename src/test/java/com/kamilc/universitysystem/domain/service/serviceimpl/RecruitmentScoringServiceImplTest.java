package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.domain.service.helper.scoringservicehelpers.ScoreCalculator;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.web.dto.scoringdtos.MissingSubjectInfoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class RecruitmentScoringServiceImplTest {

    TestDataFactory testDataFactory;

    @Mock
    ScoreCalculator scoreCalculator;

    @InjectMocks
    private RecruitmentScoringServiceImpl underTest;

    @BeforeEach
    void setUp() {
        this.testDataFactory = new TestDataFactory();
    }

    @DisplayName("Should return no missing subjects when all required are present")
    @Test
    void shouldReturnNoMissingSubjectsWhenAllRequiredPresent() {
        // Arrange
        ApplicationConfigurator appConfig = testDataFactory.sampleAppConfig();
        List<FieldOfStudy> fieldOfStudies = List.of(testDataFactory.sampleFieldOfStudies().getFirst());

        // Act
        Map<String, List<MissingSubjectInfoDTO>> missingSubjects = underTest.verifyRequiredSubjects(appConfig, fieldOfStudies);

        // Assert
        assertThat(missingSubjects).isEmpty();
    }

    @DisplayName("Should return missing subjects when some required subjects are missing")
    @Test
    void shouldReturnMissingSubjectsWhenSomeSubjectsAreMissing() {
        // Arrange
        ApplicationConfigurator appConfig = testDataFactory.sampleAppConfigMissingPhysics();
        List<FieldOfStudy> fieldOfStudies = List.of(testDataFactory.sampleFieldOfStudies().get(1)); // Robotics

        // Act
        Map<String, List<MissingSubjectInfoDTO>> missingSubjects = underTest.verifyRequiredSubjects(appConfig, fieldOfStudies);

        // Assert
        assertThat(missingSubjects)
                .containsKey("Robotics")
                .satisfies(map -> {
                    List<MissingSubjectInfoDTO> missingList = map.get("Robotics");
                    assertThat(missingList)
                        .extracting(MissingSubjectInfoDTO::getSubjectName)
                        .containsExactlyInAnyOrder("mathematics", "physics");
                });
    }

    @DisplayName("Should return all missing subjects when no subjects are provided")
    @Test
    void shouldReturnAllMissingSubjectsWhenNoSubjectsAreProvided() {
        // Arrange
        ApplicationConfigurator appConfig = testDataFactory.emptyAppConfig();
        List<FieldOfStudy> fieldOfStudies = testDataFactory.sampleFieldOfStudies();

        // Act
        Map<String, List<MissingSubjectInfoDTO>> missingSubjects = underTest.verifyRequiredSubjects(appConfig, fieldOfStudies);

        // Assert
        assertThat(missingSubjects).hasSize(2);
        assertThat(missingSubjects.keySet()).containsExactlyInAnyOrder("Information Technology", "Robotics");
    }

}