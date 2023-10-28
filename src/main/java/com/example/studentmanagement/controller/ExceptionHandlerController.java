package com.example.studentmanagement.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(NullPointerException.class)
    public String handler404Page() {
        return "error/404Page";
    }

    @ExceptionHandler(Exception.class)
    public String handlerExceptionPage() {
        return "error/errorPage";
    }
}
