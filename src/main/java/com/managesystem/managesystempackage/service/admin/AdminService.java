package com.managesystem.managesystempackage.service.admin;

import com.managesystem.managesystempackage.entity.Admin;
import com.managesystem.managesystempackage.entity.Student;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminService {
    public String adminLogin(Admin admin, HttpSession session, Model model);
    public String getStudentsInfo(Model model, Integer currentPage);
    public String studentDelete(int id);
}
