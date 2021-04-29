package com.macky.springbootshardingjdbc.controller;

import com.macky.springbootshardingjdbc.exception.JsonResult;
import com.macky.springbootshardingjdbc.exception.MyException;
import com.macky.springbootshardingjdbc.exception.WantException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private void printMsgAtConsole(Throwable e) {
        e.printStackTrace();
    }

    @ExceptionHandler(Throwable.class)
    public Object handle(Throwable e) {
        e.printStackTrace();
        HashMap<String, Object> map = new HashMap<>();
        map.put("location", "exception");
        map.put("msg", e.getMessage());
        map.put("localMsg", e.getLocalizedMessage());
        return map;
    }

    @ExceptionHandler(Exception.class)
    public Object handle(Exception e) {
        printMsgAtConsole(e);
        return new JsonResult(e);
    }

    @ExceptionHandler(MyException.class)
    public Object handle(MyException e) {
        e.printStackTrace();
        HashMap<String, Object> map = new HashMap<>();
        map.put("location", "myException");
        map.put("msg", e.getMessage());
        map.put("localMsg", e.getLocalizedMessage());
        return map;
    }

    @ExceptionHandler(WantException.class)
    public Object handle(WantException e) {
        e.printStackTrace();
        HashMap<String, Object> map = new HashMap<>();
        map.put("location", "want-to-exception");
        map.put("msg", e.getMessage());
        map.put("localMsg", e.getLocalizedMessage());
        return map;
    }
}
