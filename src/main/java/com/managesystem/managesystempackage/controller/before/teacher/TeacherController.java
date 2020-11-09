package com.managesystem.managesystempackage.controller.before.teacher;

import com.managesystem.managesystempackage.entity.Teacher;
import com.managesystem.managesystempackage.service.before.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/before/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/toLogin")
    public String toLogin(@ModelAttribute("teacher") Teacher teacher) {
        return "before/teacher/login";
    }
    @RequestMapping("/login")
    public String login(@ModelAttribute("teacher") Teacher teacher, HttpSession session, Model model) {
        return teacherService.teacherLogin(teacher, session, model);
    }
    @RequestMapping("/toRegister")
    public String toRegister(@ModelAttribute("teacher") Teacher teacher) {
        return "before/teacher/register";
    }
    @RequestMapping("/register")
    public void register(@ModelAttribute("teacher") Teacher teacher, Model model) {
        teacherService.teacherSave(teacher, model);
    }
}
