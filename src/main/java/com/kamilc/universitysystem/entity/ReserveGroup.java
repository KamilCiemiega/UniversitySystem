package com.kamilc.universitysystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reserve_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "number_of_students")
    private Integer numberOfStudents;

    @OneToMany(mappedBy = "reserveGroup", cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private List<Student> students = new ArrayList<>();

    @OneToOne(mappedBy = "reserveGroup")
    private Semester semester;
}
