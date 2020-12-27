package com.managesystem.managesystempackage.controller.before.student;

import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.service.before.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/before/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/toLogin")
    public String toLogin(@ModelAttribute("student") Student student) {
        return "before/student/login";
    }
    @RequestMapping("/login")
    public String login(@ModelAttribute("student") Student student, HttpSession session, Model model) {
        return studentService.studentLogin(student, session, model);
    }
    @RequestMapping("/toRegister")
    public String toRegister(@ModelAttribute("student") Student student) {
        return "before/student/register";
    }
    @RequestMapping("/register")
    public String register(@ModelAttribute("student") Student student, Model model) {
        return studentService.studentSave(student, model);
    }

}
