package com.kamilc.universitysystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @NotBlank(message = "Album Number is mandatory")
    @Column(name = "album_number", nullable = false, unique = true)
    private String albumNumber;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    private StudentGroup studentGroup;

    @NotBlank(message = "User Id is mandatory")
    @ManyToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "education_scope_id", nullable = true)
    private EducationScope educationScope;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdditionalSubjectEnrollment> additionalSubjectEnrollments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "reserve_group_id")
    private ReserveGroup reserveGroup;

}
