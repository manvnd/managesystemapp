package com.managesystem.managesystempackage;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//统一异常处理
@Controller
@ControllerAdvice
public class GlobalExceptionHandleController {
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        String message = "";
        //数据库异常
        if(e instanceof SQLException) {
            message = "数据库异常";
        } else if (e instanceof NoLoginException) {
            message = "未登录异常";
        } else {
            e.printStackTrace();
            message = "未知异常";
        }
        model.addAttribute("errorMessage", message);
        return "myError";
    }
}
