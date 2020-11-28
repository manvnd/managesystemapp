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
    @RequestMapping("/home")
    public String home() {
        return "before/teacher/home";
    }
    @RequestMapping("/toDutyAdd")
    public String toDutyAdd(@ModelAttribute("duty") Duty duty) {
        return "/before/teacher/dutyAdd";
    }
    @RequestMapping("dutyAdd")
    public String dutyAdd(HttpServletRequest request, @ModelAttribute("duty") Duty duty, HttpSession session, Model model) throws IOException {
        return teacherService.dutyAdd(duty, session, model);
    }
    @RequestMapping("/toDutyInfo")
    public String toDutyInfo(Model model, Integer teacherId) {
        return teacherService.toDutyInfo(model, teacherId);
    }
    @RequestMapping("/fileDownload")
    public ResponseEntity<byte[]> fileDownload(String fileName, @RequestHeader("User-Agent") String userAgent) throws IOException {
        //下载文件路径
        String path = "/Users/yucan/Downloads/Code/04/Project/managesystemapp/src/main/resources/files";
        //构建将要下载的文件对象
        File downFile = new File(path + File.separator + fileName);
        //ok表示HTTP中的状态为200
        BodyBuilder builder = ResponseEntity.ok();
        //内容长度
        builder.contentLength(downFile.length());
        //二进制流数据下载
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        //使用URLEncoder.encode对文件名进行编码
        fileName = URLEncoder.encode(fileName, "UTF-8");
        if (userAgent.indexOf("MSIE") > 0) {
            builder.header("Content-Disposition", "attachment; fileName=" + fileName);
        }
        else {
            builder.header("Content-Disposition", "attachment; filename* = UTF-8''" + fileName);
        }
        return builder.body(FileUtils.readFileToByteArray(downFile));
    }

}
