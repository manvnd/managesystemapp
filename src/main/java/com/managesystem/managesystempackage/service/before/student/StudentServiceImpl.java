package com.managesystem.managesystempackage.service.before.student;

import com.managesystem.managesystempackage.entity.Duty;
import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.repository.before.student.StudentRepository;
import com.managesystem.managesystempackage.repository.before.teacher.TeacherRepository;
import com.managesystem.managesystempackage.util.MD5Util;
import com.managesystem.managesystempackage.util.MYUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public String studentLogin(Student student, HttpSession session, Model model) {
        //对密码进行加密
        student.setStudentPwd(MD5Util.MD5(student.getStudentPwd()));
        List<Student> list = studentRepository.studentLogin(student);
        if(list.size() > 0) {//登录成功
            session.setAttribute("student", list.get(0));
            return "redirect:/before/student/home";
        }else {//登录失败
            model.addAttribute("loginMessage", "用户名或密码错误！");
            return "before/student/login";
        }
    }
    @Override
    public String studentSave(Student student) {
        if (student.getStudentNumber() != null && student.getStudentPwd() != null) {
            student.setStudentPwd(MD5Util.MD5(student.getStudentPwd()));//对学生密码进行加密
            if (studentRepository.studentSave(student) > 0) {
                return "before/student/login";
            }
        }
        return "before/student/register";
    }
    @Override
    public String home(Model model, HttpSession session) {
        return "before/student/home";
    }
    @Override
    public String getDutyList(Model model, Integer currentPage) {
        int totalCount = teacherRepository.getAllDuty();
        int pageSize = 5;
        int totalPage = (int)Math.ceil(totalCount * 1.0 / pageSize);
        List<Duty> dutiesByPage = teacherRepository.getDutyByPage((currentPage - 1) * pageSize, pageSize);
        model.addAttribute("duties", dutiesByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        System.out.println(dutiesByPage.get(0).getTeacher().getTeacherName());
        return "before/student/dutyInfo";
    }
}
