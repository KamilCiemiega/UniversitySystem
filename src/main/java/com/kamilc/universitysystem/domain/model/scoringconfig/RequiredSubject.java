package com.kamilc.universitysystem.domain.model.scoringconfig;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequiredSubject {
    private String name;
    private Double weight;
    private Boolean required;
}
