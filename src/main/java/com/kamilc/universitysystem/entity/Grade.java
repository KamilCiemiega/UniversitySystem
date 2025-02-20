package com.kamilc.universitysystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @DecimalMin(value = "2.0", message = "Grade must be at least 2.0")
    @DecimalMax(value = "5.0", message = "Grade cannot be higher than 5.0")
    @NotNull(message = "Grade is mandatory")
    @Column(name = "grade" , nullable = false)
    private Double grade;

    @NotNull(message = "Weight is mandatory")
    @Column(name = "weight", nullable = false, updatable = false)
    private Integer weight;

    @CreatedDate
    @Column(name = "graded_at", updatable = false, nullable = false)
    private LocalDateTime gradedAt;

    @Lob
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
