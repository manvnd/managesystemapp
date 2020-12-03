package com.managesystem.managesystempackage.controller.before.teacher;

import com.managesystem.managesystempackage.entity.Duty;
import com.managesystem.managesystempackage.entity.Teacher;
import com.managesystem.managesystempackage.service.before.teacher.TeacherService;
import com.managesystem.managesystempackage.util.MYUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/before/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/toLogin")
    public String toLogin(@ModelAttribute("teacher") Teacher teacher) {
        return "before/teacher/login";
    }
    @RequestMapping("/login")
    public String login(@ModelAttribute("teacher") Teacher teacher, HttpSession session, Model model) {
        return teacherService.teacherLogin(teacher, session, model);
    }
    @RequestMapping("/toRegister")
    public String toRegister(@ModelAttribute("teacher") Teacher teacher) {
        return "before/teacher/register";
    }
    @RequestMapping("/register")
    public String register(@ModelAttribute("teacher") Teacher teacher, Model model) {
        return teacherService.teacherSave(teacher, model);
    }
}
