package com.example.studentmanagement.dto;

import com.example.studentmanagement.model.Clazz;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class StudentCreateDTO implements Validator {
    @NotBlank(message = "Tên không được để trống")
    @Length( max = 50, message = "Tên quá 50 ký tự")
    @Pattern(regexp = "[a-zA-ZÀ-ỹ\\s]*", message = "Tên chỉ chứa khoảng cách hoặc chữ cái")
    private String name;

    @NotBlank(message = "Điểm không được để trống")
    private String score;

    @Valid
    private Clazz clazz;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentCreateDTO studentCreateDTO = (StudentCreateDTO) target;

        if (!studentCreateDTO.getScore().trim().equals("")) {
           try {
               double score = Double.parseDouble(studentCreateDTO.getScore());
               if(score < 0 || score > 10) {
                   errors.rejectValue("score", "", "Điểm phải trong đoạn từ [0:10]");
               }
           } catch (NumberFormatException e) {
               errors.rejectValue("score", "", "Điểm phải là số");
           }

        }
    }
}
