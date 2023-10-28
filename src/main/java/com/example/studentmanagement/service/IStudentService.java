package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentSearchDTO;
import com.example.studentmanagement.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    Page<Student> search(StudentSearchDTO studentSearchDTO, Pageable pageable);

    Student findById(int id);

    void create(Student student);
}
