package com.managesystem.managesystempackage.service.before.student;

import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.repository.before.student.StudentRepository;
import com.managesystem.managesystempackage.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public String studentLogin(Student student, HttpSession session, Model model) {
        //对密码进行加密
        student.setStudentPwd(MD5Util.MD5(student.getStudentPwd()));
        List<Student> list = studentRepository.studentLogin(student);
        if(list.size() > 0) {//登录成功
//            session.setAttribute("buser", bUser);
//            if (bUser.getIsTeacher())
            return "before/student/home";
//            else
//                return "forward:/";
        }else {//登录失败
            model.addAttribute("loginMessage", "用户名或密码错误！");
            return "before/student/login";
        }
    }
    @Override
    public String studentSave(Student student) {
        if (student.getStudentName() != null && student.getStudentPwd() != null) {
            student.setStudentPwd(MD5Util.MD5(student.getStudentPwd()));//对学生密码进行加密
            if (studentRepository.studentSave(student) > 0) {
                return "before/student/login";
            }
        }
        return "before/student/register";
    }
}
