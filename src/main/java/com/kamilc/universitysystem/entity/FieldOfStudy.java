package com.kamilc.universitysystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @NotNull(message = "Available slots can't be null")
    @Column(name = "available_slots", nullable = false)
    private Integer availableSlots;

    @OneToMany(mappedBy = "fieldOfStudy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducationScope> educationScopes = new ArrayList<>();

    @OneToMany(mappedBy = "fieldOfStudy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Semester> semesters = new ArrayList<>();

    @ManyToMany(mappedBy = "fieldsOfStudy")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public enum StudyType {
        FULL_TIME, PART_TIME
    }
}
