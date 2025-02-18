package com.kamilc.universitysystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "education_scopes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationScope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope_type", nullable = false)
    private Scope scopeType;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "field_of_study_id", nullable = false)
    private FieldOfStudy fieldOfStudy;

    @OneToMany(mappedBy = "educationScope", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "educationScope", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<EducationScope> educationScopes = new ArrayList<>();



    public enum Scope {
     OBJECT_ORIENTED_PROGRAMMING, DATA, NETWORKS
    }
}
