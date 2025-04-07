package com.kamilc.universitysystem.domain.model.confirmationstatus;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class First {
    private Boolean confirmed;
    private LocalDateTime confirmedAt;
}
