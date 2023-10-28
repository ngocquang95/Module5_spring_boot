package com.example.studentmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSearchDTO {
    private String name;
    private String fromScore;
    private String toScore;
    private String clazzId;
}
