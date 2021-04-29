package com.macky.springbootshardingjdbc.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class TestException extends MethodArgumentNotValidException {

    public TestException(MethodParameter parameter, BindingResult bindingResult) {
        super(parameter, bindingResult);
    }

    public String getMessage() {
        return super.getBindingResult().getFieldError().getDefaultMessage();
    }
}
