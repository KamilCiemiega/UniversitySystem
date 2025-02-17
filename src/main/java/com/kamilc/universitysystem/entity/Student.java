package com.kamilc.universitysystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Album Number is mandatory")
    @Column(name = "album_number")
    private String albumNumber;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudentGroup studentGroup;

    @NotBlank(message = "User Id is mandatory")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User studentUser;

}
