package com.example.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageErrorDTO {
    private String filed;
    private String message;
}
