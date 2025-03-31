package com.kamilc.universitysystem.entity;

import com.kamilc.universitysystem.domain.converter.ApplicationDataConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @NotNull(message = "Application status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @CreatedDate
    @Column(name = "applied_at", updatable = false)
    private LocalDateTime appliedAt;

    @Converter(converter = ApplicationDataConverter.class)
    @Column(name = "application_data", columnDefinition = "json", nullable = false)
    private ApplicationDataConverter application_data;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "field_of_study_id")
    private FieldOfStudy fieldOfStudy;



    public enum Status {PENDING, QUALIFIED, WAITING_LIST, REJECTED}
}
