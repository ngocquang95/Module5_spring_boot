package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.LoginDTO;
import com.example.studentmanagement.dto.RegisterDTO;

public interface IAuthService {
    String login(LoginDTO loginDto);

    String register(RegisterDTO registerDto);
}
