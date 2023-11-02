package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentSearchDTO;
import com.example.studentmanagement.model.Clazz;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.IClazzService;
import com.example.studentmanagement.service.impl.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clazzes")
public class ClazzController {
    @Autowired
    private IClazzService clazzService;

    @GetMapping()
    public ResponseEntity<List<Clazz>> getClazzes() {
        return new ResponseEntity<>(clazzService.findAll(), HttpStatus.OK);
    }
}
