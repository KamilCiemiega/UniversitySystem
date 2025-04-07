package com.kamilc.universitysystem.domain.model.confirmationstatus;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Second {
    private Boolean confirmed;
    private LocalDateTime confirmedAt;
}
