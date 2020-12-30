package com.managesystem.managesystempackage.service.admin;

import com.managesystem.managesystempackage.entity.Admin;
import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.entity.Teacher;
import com.managesystem.managesystempackage.repository.admin.AdminRepository;
import com.managesystem.managesystempackage.util.MD5Util;
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
            session.setAttribute("admin", list.get(0));
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
        return "redirect:/admin/allStudentInfo?currentPage=1";
    }
    @Override
    public String studentAdd(Student student) {
        student.setStudentPwd(MD5Util.MD5("123456"));
        adminRepository.studentAdd(student);
        return "redirect:/admin/allStudentInfo?currentPage=1";
    }
    @Override
    public String studentUpdate(Student student) {
        if (student.getStudentPwd().equals("")) {
            adminRepository.studentUpdateExceptPwd(student);
        }
        else {
            student.setStudentPwd(MD5Util.MD5(student.getStudentPwd()));
            adminRepository.studentUpdateConcludePwd(student);
        }
        return "redirect:/admin/allStudentInfo?currentPage=1";
    }
    @Override
    public Student selectOneStudent(Integer id) {
        return adminRepository.selectOneStudent(id);
    }
    @Override
    public String getTeachersInfo(Model model, Integer currentPage) {
        //共多少个老师
        int totalCount = adminRepository.selectAllTeachers();
        //计算共多少页
        int pageSize = 5;
        int totalPage = (int)Math.ceil(totalCount * 1.0 / pageSize);
        List<Teacher> teachersByPage = adminRepository.selectTeachersByPage((currentPage - 1) * pageSize, pageSize);
        model.addAttribute("teachers", teachersByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        return "/admin/allTeacherInfo";
    }
    @Override
    public String teacherDelete(int id) {
        adminRepository.teacherDelete(id);
        return "redirect:/admin/allTeacherInfo?currentPage=1";
    }
    @Override
    public String teacherAdd(Teacher teacher) {
        teacher.setTeacherPwd(MD5Util.MD5("123456"));
        adminRepository.teacherAdd(teacher);
        return "redirect:/admin/allTeacherInfo?currentPage=1";
    }
    @Override
    public String teacherUpdate(Teacher teacher) {
        if (teacher.getTeacherPwd().equals("")) {
            adminRepository.teacherUpdateExceptPwd(teacher);
        }
        else {
            teacher.setTeacherPwd(MD5Util.MD5(teacher.getTeacherPwd()));
            adminRepository.teacherUpdateConcludePwd(teacher);
        }
        return "redirect:/admin/allTeacherInfo?currentPage=1";
    }
    @Override
    public Teacher selectOneTeacher(Integer id) {
        return adminRepository.selectOneTeacher(id);
    }
}
