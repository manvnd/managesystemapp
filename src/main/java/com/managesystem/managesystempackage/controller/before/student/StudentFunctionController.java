package com.managesystem.managesystempackage.controller.before.student;

import com.managesystem.managesystempackage.service.before.student.StudentFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/before/student")
public class StudentFunctionController extends StudentBaseController{
    @Autowired
    StudentFunctionService studentFunctionService;
    @RequestMapping("/home")
    public String home(Model model, HttpSession session) {
        return studentFunctionService.home(model, session);
    }
}
