package com.managesystem.managesystempackage.service.admin;

import com.managesystem.managesystempackage.entity.Admin;
import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.entity.Teacher;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminService {
    public String adminLogin(Admin admin, HttpSession session, Model model);
    public String getStudentsInfo(Model model, Integer currentPage);
    public String studentDelete(Model model, int id);
    public String studentAdd(Model model, Student student);
    public String studentUpdate(Model model, Student student);
    public Student selectOneStudent(Integer id);
    public String getTeachersInfo(Model model, Integer currentPage);
    public String teacherDelete(Model model, int id);
    public String teacherAdd(Model model, Teacher teacher);
    public String teacherUpdate(Model model, Teacher teacher);
    public Teacher selectOneTeacher(Integer id);
}
