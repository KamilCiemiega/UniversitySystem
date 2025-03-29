package com.kamilc.universitysystem.entity;

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
@Table(name = "students_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @NotBlank(message = "Name of group is mandatory")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull(message = "Group size is required")
    @Column(name = "group_size", nullable = false)
    private Integer groupSize;

    @OneToMany(mappedBy = "studentGroup", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<Student> students = new ArrayList<>();

    @NotNull(message = "Semester id is required")
    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;
}
