package com.managesystem.managesystempackage.controller.admin;


import com.managesystem.managesystempackage.entity.AUser;
import com.managesystem.managesystempackage.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/toLogin")
    public String toLogin(@ModelAttribute("aUser") AUser aUser) {
        return "admin/login";
    }
    @RequestMapping("/login")
    public String login(@ModelAttribute("aUser") AUser aUser, HttpSession session, Model model) {
        return adminService.adminLogin(aUser, session, model);
    }
    @RequestMapping("/allTeacherInfo")
    public String allTeacherInfo() {
        return "admin/allTeacherInfo";
    }
}
