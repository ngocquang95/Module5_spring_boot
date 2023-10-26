package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.model.Clazz;
import com.example.studentmanagement.repository.IClazzRepository;
import com.example.studentmanagement.service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzService implements IClazzService {
    @Autowired
    private IClazzRepository clazzRepository;
    @Override
    public List<Clazz> findAll() {
        return clazzRepository.findAll();
    }
}
