package com.kamilc.universitysystem.web.dto.applicationdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDTO {
    private Integer id;
    private String Status;
    private LocalDateTime appliedAt;
}
