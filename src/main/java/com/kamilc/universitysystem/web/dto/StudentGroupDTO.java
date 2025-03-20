package com.kamilc.universitysystem.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroupDTO {
    private Integer id;
    private String name;
    private String groupType;


}
