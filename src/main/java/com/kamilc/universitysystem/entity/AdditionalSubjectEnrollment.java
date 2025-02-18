package com.kamilc.universitysystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "additional_subject_enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalSubjectEnrollment {

    @EmbeddedId
    private AdditionalSubjectEnrollmentId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @CreatedDate
    @Column(name = "enrolled_at", nullable = false, updatable = false)
    private Timestamp enrolledAt;

}
