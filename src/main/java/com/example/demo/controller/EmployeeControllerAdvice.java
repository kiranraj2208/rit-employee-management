package com.example.demo.controller;


import com.example.demo.model.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<MyException> requestValidationError(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        return bindingResult.getFieldErrors().stream().map(error -> new MyException(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public MyException runtimeException(RuntimeException runtimeException) {
        return new MyException("5001", runtimeException.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public MyException exception() {
        return new MyException("5000", "There was some error while processing the request");
    }
}
