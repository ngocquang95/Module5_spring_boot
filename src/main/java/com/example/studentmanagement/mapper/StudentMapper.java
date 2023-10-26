package com.example.studentmanagement.mapper;

import com.example.studentmanagement.dto.StudentCreateDTO;
import com.example.studentmanagement.model.Student;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component("studentMapper")
public interface StudentMapper {
    Student toStudentFromStudentCreateDTO(StudentCreateDTO studentCreateDTO);
}
