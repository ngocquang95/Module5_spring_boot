package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentSearchDTO;
import com.example.studentmanagement.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> search(StudentSearchDTO studentSearchDTO);

    Student findById(int id);

    void create(Student student);
}
