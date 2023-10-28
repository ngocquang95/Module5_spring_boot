package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.StudentSearchDTO;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.IStudentRepository;
import com.example.studentmanagement.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public Page<Student> search(StudentSearchDTO studentSearchDTO, Pageable pageable) {
        // Tránh trường hợp người dùng đi vào màn hình list (Chưa search)
        if(studentSearchDTO.getName() == null) {
            studentSearchDTO.setName("");
        }

        if("".equals(studentSearchDTO.getFromScore())) {
            studentSearchDTO.setFromScore(null);
        }

        if("".equals(studentSearchDTO.getToScore())) {
            studentSearchDTO.setToScore(null);
        }

//        return studentRepository.search(studentSearchDTO);
        //return studentRepository.findByNameContaining(studentSearchDTO.getName());
        return studentRepository.search(studentSearchDTO.getName(),
                studentSearchDTO.getFromScore(),
                studentSearchDTO.getToScore(),
                studentSearchDTO.getClazzId(), pageable);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).orElseThrow(NullPointerException::new); // Java 8 => cú pháp ngắn gọn
    }

    @Override
    public void create(Student student) {
        studentRepository.save(student);
        /*
        nếu như khóa chính không tồn tại ở DB: thêm mới
        Nếu khóa chính trùng với id ở trong DB: cập nhập
         */
    }
}
