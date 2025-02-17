package com.kamilc.universitysystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "grade", precision = 3, scale = 1, nullable = false)
    @DecimalMin(value = "2.0", message = "Grade must be at least 2.0")
    @DecimalMax(value = "5.0", message = "Grade cannot be higher than 5.0")
    @NotBlank(message = "Grade is mandatory")
    private Double grade;

    @NotBlank(message = "Weight is mandatory")
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @CreatedDate
    @Column(name = "graded_at")
    private Timestamp gradedAt;

    @Lob
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;
}
