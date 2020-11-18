package com.managesystem.managesystempackage.service.admin;

import com.managesystem.managesystempackage.entity.Admin;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface AdminService {
    public String adminLogin(Admin admin, HttpSession session, Model model);
}
