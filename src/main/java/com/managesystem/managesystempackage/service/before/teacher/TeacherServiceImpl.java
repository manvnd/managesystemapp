package com.managesystem.managesystempackage.service.before.teacher;

import com.managesystem.managesystempackage.entity.*;
import com.managesystem.managesystempackage.repository.before.student.StudentRepository;
import com.managesystem.managesystempackage.repository.before.teacher.TeacherRepository;
import com.managesystem.managesystempackage.util.MD5Util;
import com.managesystem.managesystempackage.util.MYUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public String teacherLogin(Teacher teacher, HttpSession session, Model model) {
        teacher.setTeacherPwd(MD5Util.MD5(teacher.getTeacherPwd()));
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
        if (teacher.getTeacherName() != null && teacher.getTeacherPwd() != null) {
            teacher.setTeacherPwd(MD5Util.MD5(teacher.getTeacherPwd()));//对教师密码进行加密
            if (teacherRepository.teacherSave(teacher) > 0) {
                return "before/teacher/login";
            }
        }
        return "no";
    }
    @Override
    public String dutyAdd(Duty duty, HttpSession session, Model model) throws IOException {
        MultipartFile dutyFile = duty.getDutyFileName();
        if (!dutyFile.isEmpty()) {
            //上传文件路径
            String path = "C:\\Users\\WangTian\\Desktop\\managesystemapp\\src\\main\\resources\\files";
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
            duty.setDutyDescriptionFile(fileNewName);
        }
        duty.setTeacherId(MYUtil.getTeacher(session).getTeacherId());
        duty.setDutySelectedNumber(0);
        teacherRepository.dutyAdd(duty);
        return "before/teacher/home";
    }
    @Override
    public String toDutyInfo(Model model, Integer teacherId, Integer currentPage) {
        if (teacherRepository.checkDutyByTeacherId(teacherId) == 0) {
            model.addAttribute("message", "您还未发布综设课程");
            return "before/teacher/home";
        }
        //共多少个学生
        int totalCount = teacherRepository.getAllDutyByTeacherId(teacherId);
        if (totalCount ==0 ) {
            model.addAttribute("message", "还没有学生选择您的综设课程");
            return "before/teacher/home";
        }
        //计算共多少页
        int pageSize = 5;
        int totalPage = (int)Math.ceil(totalCount * 1.0 / pageSize);
        List<Duty> dutiesByPage = teacherRepository.getDutyByTeacherIdByPage(teacherId, (currentPage - 1) * pageSize, pageSize);
        model.addAttribute("duties", dutiesByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        return "before/teacher/myDutyInfo";
    }
    @Override
    public String toCheckStudentGroupProcess(Model model, Integer dutyId, HttpSession session) {
        StudentGroup studentGroupList = teacherRepository.getStudentGroupProcessByDutyId(dutyId);

        if (studentGroupList == null) {
            Teacher teacher = MYUtil.getTeacher(session);
            model.addAttribute("message", "还没有学生选择您的课程");
            return "forward:/before/teacher/toDutyInfo?teacherId=" + teacher.getTeacherId() + "&currentPage=1";
        }

        model.addAttribute("studentGroupProcess", studentGroupList);
        return "before/teacher/studentGroupProcess";
    }
    @Override
    public Duty selectOneDuty(Integer dutyId) {
        return teacherRepository.selectOneDuty(dutyId);
    }
    @Override
    public String updateDuty(Duty duty, HttpSession session) throws IOException {
        MultipartFile dutyFile = duty.getDutyFileName();
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
            duty.setDutyDescriptionFile(fileNewName);
            teacherRepository.updateDutyByDutyConcludeFile(duty);
        }
        else {
            teacherRepository.updateDutyByDutyExceptFile(duty);
        }
        Teacher teacher = MYUtil.getTeacher(session);
        return "forward:/before/teacher/toDutyInfo?teacherId=" + teacher.getTeacherId() + "&currentPage=1";
    }
    @Override
    public String toLeaderSet(Model model, Integer studentGroupNumber) {
        List<StudentGroupMember> studentGroupInfo = teacherRepository.getStudentNameAndIdByStudentGroupNumber(studentGroupNumber);
        model.addAttribute("studentGroupInfo", studentGroupInfo);
        return "/before/teacher/leaderSet";
    }
    @Override
    public String leaderSet(Model model, HttpSession session, Integer studentId) {
        System.out.println(studentId);
        int studentGroupNumber = studentRepository.getStudentGroupNumberByStudentId(studentId);
        System.out.println(studentGroupNumber);
        if (teacherRepository.setLeaderByStudentId(studentId, studentGroupNumber) > 0) {
            model.addAttribute("message", "任命组长成功!");
        }
        Teacher teacher = MYUtil.getTeacher(session);
        return "forward:/before/teacher/toCheckStudentGroupProcess?dutyId=" + studentGroupNumber;
    }
    @Override
    public StudentGroup getStudentGroupInfoByStudentNumber(Integer studentGroupNumber) {
        return teacherRepository.getStudentGroupInfoByStudentNumber(studentGroupNumber);
    }
    @Override
    public String gradeSet(StudentGroup studentGroup, Model model) {
        teacherRepository.updateGrade(studentGroup);
        return "forward:/before/teacher/toCheckStudentGroupProcess?dutyId=" + studentGroup.getStudentGroupNumber();
    }
    @Override
    public boolean checkFirstReport(Integer studentGroupNumber) {
        return teacherRepository.checkFirstReport(studentGroupNumber) == null;
    }
    @Override
    public boolean checkSecondReport(Integer studentGroupNumber) {
        return teacherRepository.checkSecondReport(studentGroupNumber) == null;
    }
    @Override
    public boolean checkThirdReport(Integer studentGroupNumber) {
        return teacherRepository.checkThirdReport(studentGroupNumber) == null;
    }
}
