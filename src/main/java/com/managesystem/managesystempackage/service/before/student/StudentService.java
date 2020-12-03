package com.managesystem.managesystempackage.service.before.student;

import com.managesystem.managesystempackage.entity.Student;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface StudentService {
    public String studentLogin(Student student, HttpSession session, Model model);
    public String studentSave(Student student);
    public String home(Model model, HttpSession session);
    public String getDutyList(Model model, Integer currentPage);
}
