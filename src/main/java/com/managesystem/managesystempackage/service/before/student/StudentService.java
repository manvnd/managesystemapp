package com.managesystem.managesystempackage.service.before.student;

import com.managesystem.managesystempackage.entity.Student;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface StudentService {
    public String studentLogin(Student student, HttpSession session, Model model);
    public void studentSave(Student student, Model model);
}
