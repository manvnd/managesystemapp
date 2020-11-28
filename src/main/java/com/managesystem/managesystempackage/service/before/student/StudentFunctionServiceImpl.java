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
        return "before/student/home";
    }
}
