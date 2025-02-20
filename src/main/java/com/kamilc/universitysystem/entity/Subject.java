package com.kamilc.universitysystem.entity;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type", nullable = false)
    private SubjectType subjectType;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_category", nullable = false)
    private SubjectCategory subjectCategory;

    @ManyToOne
    @JoinColumn(name = "semester_id",
            foreignKey = @ForeignKey(
                    name = "fk_semester", value = ConstraintMode.CONSTRAINT
            ))
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "education_scope_id")
    private EducationScope educationScope;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @OneToMany(mappedBy = "subject", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<Grade> grades = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdditionalSubjectEnrollment> additionalSubjectEnrollments = new ArrayList<>();


    public enum SubjectType {CORE, ADDITIONAL}
    public enum SubjectCategory {LECTURE, LABORATORY}
}
