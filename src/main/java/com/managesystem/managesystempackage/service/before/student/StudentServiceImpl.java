package com.managesystem.managesystempackage.service.before.student;

import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.repository.before.student.StudentRepository;
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
    public void studentSave(Student student, Model model) {
        studentRepository.studentSave(student);
    }
}
