package com.managesystem.managesystempackage.service.before.student;

import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.util.MYUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class StudentFunctionServiceImpl implements StudentFunctionService{
    @Override
    public String home(Model model, HttpSession session) {
        model.addAttribute("studentName", MYUtil.getStudent(session).getName());
        System.out.println(session.getAttribute("student"));
        Student student = (Student)session.getAttribute("student");
        System.out.println(student.getName());
        System.out.println(MYUtil.getStudent(session).getName());
        return "before/student/home";
    }
}
