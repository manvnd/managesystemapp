package com.managesystem.managesystempackage.service.before.teacher;

import com.managesystem.managesystempackage.entity.Teacher;
import com.managesystem.managesystempackage.repository.before.teacher.TeacherRepository;
import com.managesystem.managesystempackage.util.MD5Util;
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
        teacher.setPwd(MD5Util.MD5(teacher.getPwd()));
        List<Teacher> list = teacherRepository.teacherLogin(teacher);
        if(list.size() > 0) {//登录成功
            session.setAttribute("teacher", teacher);
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
    public String teacherSave(Teacher teacher, Model model) {
        if (teacher.getName() != null && teacher.getPwd() != null) {
            teacher.setPwd(MD5Util.MD5(teacher.getPwd()));//对教师密码进行加密
            if (teacherRepository.teacherSave(teacher) > 0) {
                return "before/teacher/login";
            }
        }
        return "no";
    }
}
