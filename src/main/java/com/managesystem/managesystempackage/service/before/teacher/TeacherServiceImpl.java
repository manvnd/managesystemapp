package com.managesystem.managesystempackage.service.before.teacher;

import com.managesystem.managesystempackage.entity.Duty;
import com.managesystem.managesystempackage.entity.Teacher;
import com.managesystem.managesystempackage.repository.before.teacher.TeacherRepository;
import com.managesystem.managesystempackage.util.MD5Util;
import com.managesystem.managesystempackage.util.MYUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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
            session.setAttribute("teacher", list.get(0));
            return "redirect:/before/teacher/home";
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
    @Override
    public String dutyAdd(Duty duty, HttpSession session, Model model) throws IOException {
        MultipartFile dutyFile = duty.getFileName();
        if (!dutyFile.isEmpty()) {
            //上传文件路径
            String path = "/Users/yucan/Downloads/Code/04/Project/managesystemapp/src/main/resources/files";
//            String path = request.getServletContext().getRealPath("/files/");
            //获取上传文件原名
            String fileName = dutyFile.getOriginalFilename();
            //对文件重命名
            String fileNewName = MYUtil.getNewFileName(fileName);
            File filePath = new File(path + File.separator + fileNewName);
            //如果文件目录不存在，创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            } else {
                //将上传文件保存到另一个目标文件中
                dutyFile.transferTo(filePath);
            }
            duty.setDescriptionFile(fileNewName);
        }
        duty.setTeacherId(MYUtil.getTeacher(session).getId());
        duty.setSelectedNumber(0);
        teacherRepository.dutyAdd(duty);
        return "before/teacher/home";
    }
    @Override
    public String toDutyInfo(Model model, Integer teacherId) {
        List<Duty> duties = teacherRepository.getDutyByTeacherId(teacherId);
        model.addAttribute("duties", duties);
        return "before/teacher/myDutyInfo";
    }

}
