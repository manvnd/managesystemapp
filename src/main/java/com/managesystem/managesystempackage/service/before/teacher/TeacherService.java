package com.managesystem.managesystempackage.service.before.teacher;

import com.managesystem.managesystempackage.entity.Duty;
import com.managesystem.managesystempackage.entity.Teacher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface TeacherService {
    public String teacherLogin(Teacher teacher, HttpSession session, Model model);
    public String teacherSave(Teacher teacher, Model model);
    public String dutyAdd(Duty duty, HttpSession session, Model model) throws IOException;
    public String toDutyInfo(Model model, Integer teacherId);
}
