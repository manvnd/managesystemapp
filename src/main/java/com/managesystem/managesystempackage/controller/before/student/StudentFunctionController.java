package com.managesystem.managesystempackage.controller.before.student;

import com.managesystem.managesystempackage.service.before.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/before/student")
public class StudentFunctionController extends StudentBaseController{
    @Autowired
    private StudentService studentService;
    @RequestMapping("/home")
    public String home(Model model, HttpSession session) {
        return studentService.home(model, session);
    }
    @RequestMapping("/toDutyInfo")
    public String toDutyInfo(Model model, Integer currentPage) {
        return studentService.getDutyList(model, currentPage);
    }
}
