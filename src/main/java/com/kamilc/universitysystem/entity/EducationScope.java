package com.kamilc.universitysystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "education_scopes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationScope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope_type")
    private Scope scopeType;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "field_of_study_id")
    private FieldOfStudy fieldOfStudy;

    public enum Scope {
     OBJECT_ORIENTED_PROGRAMMING, DATA, NETWORKS
    }
}
