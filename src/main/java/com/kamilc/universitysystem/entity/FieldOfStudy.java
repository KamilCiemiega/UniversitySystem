package com.kamilc.universitysystem.entity;

import com.kamilc.universitysystem.domain.converter.ScoringConverter;
import com.kamilc.universitysystem.domain.model.scoringconfig.ScoringConfiguration;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fields_of_study")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldOfStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @NotBlank(message = "Field of study name is required")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull(message = "Study type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "study_type", nullable = false)
    private FieldOfStudy.StudyType studyType;

    @Convert(converter = ScoringConverter.class)
    @Column(name = "config", columnDefinition = "json", nullable = false)
    private ScoringConfiguration scoringConfig;

    @Column(name = "confirmation_count", columnDefinition = "int default 0")
    private int confirmationCount;

    @Column(name = "min_score")
    private int minScore;

    @Column(name = "available_slots")
    private int availableSlots;

    @Column(name = "min_required_confirmations")
    private int minRequiredConfirmations;

    @OneToMany(mappedBy = "fieldOfStudy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducationScope> educationScopes = new ArrayList<>();

    @OneToMany(mappedBy = "fieldOfStudy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Semester> semesters = new ArrayList<>();

    @ManyToMany(mappedBy = "fieldsOfStudy")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "fieldOfStudy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();

    public enum StudyType {
        FULL_TIME, PART_TIME
    }
}
