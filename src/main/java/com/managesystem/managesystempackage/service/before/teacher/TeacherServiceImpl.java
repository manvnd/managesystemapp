package com.managesystem.managesystempackage.service.before.teacher;

import com.managesystem.managesystempackage.entity.Teacher;
import com.managesystem.managesystempackage.repository.before.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public String teacherLogin(Teacher teacher, HttpSession session, Model model) {
        List<Teacher> list = teacherRepository.teacherLogin(teacher);
        if(list.size() > 0) {//登录成功
//            session.setAttribute("buser", bUser);
//            if (bUser.getIsTeacher())
            return "before/teacher/home";
//            else
//                return "forward:/";
        }else {//登录失败
            model.addAttribute("loginMessage", "用户名或密码错误！");
            return "before/teacher/login";
        }
    }
    @Override
    public void teacherSave(Teacher teacher, Model model) {
        teacherRepository.teacherSave(teacher);
    }
}
