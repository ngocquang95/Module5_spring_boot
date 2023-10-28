package com.example.studentmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private int id;
    private String name;
    private double score;
    private int clazzId;
    private String clazzName;
}
