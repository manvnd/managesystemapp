package com.managesystem.managesystempackage.controller.admin;

import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.entity.Teacher;
import com.managesystem.managesystempackage.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminFunctionController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/home")
    public String home() {
        return "admin/home";
    }
    @RequestMapping("/allStudentInfo")
    public String allStudentInfo(Model model, Integer currentPage) {
        return adminService.getStudentsInfo(model, currentPage);
    }
    @RequestMapping("/studentDelete")
    public String studentDelete(int id) {
        return adminService.studentDelete(id);
    }
    @RequestMapping("/toStudentAdd")
    public String toStudentAdd(@ModelAttribute("student") Student student) {
        return "admin/studentAdd";
    }
    @RequestMapping("/studentAdd")
    public String studentAdd(@ModelAttribute("student") Student student) {
        return adminService.studentAdd(student);
    }
    @RequestMapping("/toStudentUpdate")
    public String toStudentUpdate(Model model, Integer id) {
        Student student = adminService.selectOneStudent(id);
        model.addAttribute("student", student);
        return "admin/studentUpdate";
    }
    @RequestMapping("/studentUpdate")
    public String studentUpdate(@ModelAttribute("student") Student student) {
        return adminService.studentUpdate(student);
    }
    @RequestMapping("/allTeacherInfo")
    public String allTeacherInfo(Model model, Integer currentPage) {
        return adminService.getTeachersInfo(model, currentPage);
    }
    @RequestMapping("/teacherDelete")
    public String teacherDelete(int id) {
        return adminService.teacherDelete(id);
    }
    @RequestMapping("/toTeacherAdd")
    public String toTeacherAdd(@ModelAttribute("teacher") Teacher teacher) {
        return "admin/teacherAdd";
    }
    @RequestMapping("/teacherAdd")
    public String teacherAdd(@ModelAttribute("teacher") Teacher teacher) {
        return adminService.teacherAdd(teacher);
    }
    @RequestMapping("/toTeacherUpdate")
    public String toTeacherUpdate(Model model, Integer id) {
        Teacher teacher = adminService.selectOneTeacher(id);
        model.addAttribute("teacher", teacher);
        return "admin/teacherUpdate";
    }
    @RequestMapping("/teacherUpdate")
    public String teacherUpdate(@ModelAttribute("teacher") Teacher teacher) {
        return adminService.teacherUpdate(teacher);
    }
}
