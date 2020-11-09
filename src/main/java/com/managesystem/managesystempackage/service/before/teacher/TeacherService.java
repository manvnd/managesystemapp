package com.managesystem.managesystempackage.service.before.teacher;

import com.managesystem.managesystempackage.entity.Teacher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

public interface TeacherService {
    public String teacherLogin(Teacher teacher, HttpSession session, Model model);
    public void teacherSave(Teacher teacher, Model model);
}
