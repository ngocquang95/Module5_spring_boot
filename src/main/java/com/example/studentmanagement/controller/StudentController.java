package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.MessageErrorDTO;
import com.example.studentmanagement.dto.StudentCreateDTO;
import com.example.studentmanagement.dto.StudentSearchDTO;
import com.example.studentmanagement.dto.StudentUpdateDTO;
import com.example.studentmanagement.mapper.StudentMapper;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.IClazzService;
import com.example.studentmanagement.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    @Qualifier("studentService") // Chọn triển khai theo tên bean
    private IStudentService studentService;

    @Autowired
    private StudentMapper studentMapper;
    // Restful API

    // Làm về BE
    @GetMapping()
    public ResponseEntity<Page<Student>> getStudents(StudentSearchDTO studentSearchDTO,
                                                  @PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(studentService.search(studentSearchDTO, pageable), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        Student student = studentService.findById(id);

        if(student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@Validated @RequestBody StudentCreateDTO studentCreateDTO, BindingResult bindingResult) {
        new StudentCreateDTO().validate(studentCreateDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            List<MessageErrorDTO> messageErrorDTOS = new ArrayList<>();
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                messageErrorDTOS.add(new MessageErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()));
            }
            return new ResponseEntity<>(messageErrorDTOS, HttpStatus.BAD_REQUEST);
        }

        studentService.save(studentMapper.toStudentFromStudentCreateDTO(studentCreateDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Validated @RequestBody StudentUpdateDTO studentUpdateDTO, BindingResult bindingResult) {
        if(studentService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        new StudentUpdateDTO().validate(studentUpdateDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            List<MessageErrorDTO> messageErrorDTOS = new ArrayList<>();
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                messageErrorDTOS.add(new MessageErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()));
            }
            return new ResponseEntity<>(messageErrorDTOS, HttpStatus.BAD_REQUEST);
        }

        Student student = studentMapper.toStudentFromStudentUpdateDTO(studentUpdateDTO);
        student.setId(id);
        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") int id) {
        Student student = studentService.findById(id);

        if(student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studentService.delete(student);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
