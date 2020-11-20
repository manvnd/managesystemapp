package com.managesystem.managesystempackage.service.admin;

import com.managesystem.managesystempackage.entity.Admin;
import com.managesystem.managesystempackage.entity.Student;
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
    @Override
    public String getStudentsInfo(Model model, Integer currentPage) {
        //共多少个学生
        int totalCount = adminRepository.selectAllStudents();
        //计算共多少页
        int pageSize = 5;
        int totalPage = (int)Math.ceil(totalCount * 1.0 / pageSize);
        List<Student> studentsByPage = adminRepository.selectStudentsByPage((currentPage - 1) * pageSize, pageSize);
        model.addAttribute("students", studentsByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        return "/admin/allStudentInfo";
    }
    @Override
    public String studentDelete(int id) {
        adminRepository.studentDelete(id);
        return "redirect:/admin/allStudentInfo";
    }
}
