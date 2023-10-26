package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Clazz;
import com.example.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClazzRepository extends JpaRepository<Clazz, Integer> {
}
