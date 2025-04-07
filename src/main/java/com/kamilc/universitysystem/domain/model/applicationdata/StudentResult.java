package com.kamilc.universitysystem.domain.model.applicationdata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResult {
    private String subject;
    private String level;
    private int score;
}
