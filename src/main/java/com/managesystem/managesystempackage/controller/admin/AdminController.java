package com.managesystem.managesystempackage.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.managesystem.managesystempackage.entity.Admin;
import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/toLogin")
    public String toLogin(@ModelAttribute("admin") Admin admin) {
        return "admin/login";
    }
    @RequestMapping("/login")
    public String login(@ModelAttribute("admin") Admin admin, HttpSession session, Model model) {
        return adminService.adminLogin(admin, session, model);
    }
    @RequestMapping("/allTeacherInfo")
    public String allTeacherInfo(Model model) {
        return "admin/allTeacherInfo";
    }
    @RequestMapping("/allStudentInfo")
    public String allStudentInfo(Model model, Integer currentPage) {
        return adminService.getStudentsInfo(model, currentPage);
    }
    @RequestMapping("/studentDelete")
    public String studentDelete(int id) {
        return adminService.studentDelete(id);
    }
}
