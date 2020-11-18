package com.managesystem.managesystempackage.service.before.student;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface StudentFunctionService {
    public String home(Model model, HttpSession session);
}
