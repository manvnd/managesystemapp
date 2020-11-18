package com.managesystem.managesystempackage.service.admin;

import com.managesystem.managesystempackage.entity.Admin;
import com.managesystem.managesystempackage.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public String adminLogin(Admin admin, HttpSession session, Model model) {
        List<Admin> list = adminRepository.adminLogin(admin);
        if(list.size() > 0) {//登录成功
            session.setAttribute("admin", admin);
            return "admin/home";
        }else {//登录失败
            model.addAttribute("loginMessage", "用户名或密码错误");
            return "admin/login";
        }
    }
}
