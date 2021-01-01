package com.managesystem.managesystempackage.service.before.student;

import com.managesystem.managesystempackage.entity.Duty;
import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.entity.StudentGroup;
import com.managesystem.managesystempackage.entity.StudentGroupMember;
import com.managesystem.managesystempackage.repository.before.student.StudentRepository;
import com.managesystem.managesystempackage.repository.before.teacher.TeacherRepository;
import com.managesystem.managesystempackage.util.MD5Util;
import com.managesystem.managesystempackage.util.MYUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    public String studentSave(Student student, Model model) {
        if (student.getStudentNumber() != null && student.getStudentPwd() != null) {
            if (studentRepository.checkStudentNumber(student.getStudentNumber()) != null) {
                model.addAttribute("message", "该学号已被注册!");
                return "before/student/register";
            }
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
    public String getDutyList(Model model, HttpSession session, Integer currentPage) {
        if (teacherRepository.getAllDuty() == 0) {
            model.addAttribute("message", "暂无老师发布综合设计");
            return "/before/student/home";
        }
        int totalCount = teacherRepository.getAllDuty();
        int pageSize = 5;
        int totalPage = (int)Math.ceil(totalCount * 1.0 / pageSize);
        List<Duty> dutiesByPage = teacherRepository.getDutyByPage((currentPage - 1) * pageSize, pageSize);
        model.addAttribute("duties", dutiesByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        if (studentRepository.checkStudentGroupNumberByStudentId(MYUtil.getStudent(session).getStudentId()) == 0) {
            model.addAttribute("studentSelected", false);
        } else {
            model.addAttribute("studentSelected", true);
        }
        return "before/student/dutyInfo";
    }
    @Override
    public String dutyChoose(Integer dutyId, HttpSession session) {
        int studentId = MYUtil.getStudent(session).getStudentId();
        int dutySelectedStudentNumber = studentRepository.getDutySelectedStudentNumber(dutyId) + 1;

        if (studentRepository.studentGroupInsert(dutyId, studentId, dutySelectedStudentNumber, MYUtil.getStudent(session).getStudentName()) > 0) {
            return "redirect:/before/student/toDutyInfo?currentPage=1";
        }
        else {
            return "no";
        }
    }
    @Override
    public String studentGroupSelectByStudentId(Model model, Integer studentId) {
        if (studentRepository.checkStudentGroupNumberByStudentId(studentId) == 0) {
            model.addAttribute("message", "您还未选择综设课程");
            return "/before/student/home";
        }
        int studentGroupNumber = studentRepository.getStudentGroupNumberByStudentId(studentId);
        List<StudentGroupMember> studentGroups = studentRepository.getStudentGroupByStudentGroupNumber(studentGroupNumber);
        model.addAttribute("studentGroupInfo", studentGroups);
        model.addAttribute("test", "王天");
        return "/before/student/groupInfo";
    }
    @Override
    public String studentGroupDutySelectByStudentId(Model model, Integer studentId) {
        if (studentRepository.checkStudentGroupNumberByStudentId(studentId) == 0) {
            model.addAttribute("message", "您还未选择综设课程");
            return "/before/student/home";
        }
        int studentGroupNumber = studentRepository.getStudentGroupNumberByStudentId(studentId);
        StudentGroup studentGroup = studentRepository.getStudentGroupDutyByStudentGroupNumber(studentGroupNumber);
        if (studentRepository.checkStudentLeader(studentId).equals("是")) {
            model.addAttribute("isLeader", true);
        } else {
            model.addAttribute("isLeader", false);
        }
        model.addAttribute("studentGroupDutyInfo", studentGroup);
        return "/before/student/dutyProcessInfo";
    }
    @Override
    public String toUploadReportFile(Integer studentGroupNumber, HttpSession session, Model model, String fileName) {
        StudentGroup studentGroup = teacherRepository.getStudentGroupInfoByStudentNumber(studentGroupNumber);
        if (fileName.equals("one")) {
            return "/before/student/uploadReportFileOne";
        } else if (fileName.equals("two")) {
            if (studentGroup.getStudentGroupReportOneFile() != null) {
                return "/before/student/uploadReportFileTwo";
            } else {
                model.addAttribute("message", "你们小组还未上传第一次报告");
                return "forward:/before/student/toMyDutyProcess?studentId=" + MYUtil.getStudent(session).getStudentId();
            }
        } else {
            if (studentGroup.getStudentGroupReportOneFile() != null) {
                if (studentGroup.getStudentGroupReportTwoFile() != null) {
                    return "/before/student/uploadReportFileThree";
                } else {
                    model.addAttribute("message", "你们小组还未上传第二次报告");
                    return "forward:/before/student/toMyDutyProcess?studentId=" + MYUtil.getStudent(session).getStudentId();
                }
            } else {
                model.addAttribute("message", "你们小组还未上传第一次报告");
                return "forward:/before/student/toMyDutyProcess?studentId=" + MYUtil.getStudent(session).getStudentId();
            }

        }
    }
    @Override
    public String uploadReportFileOne(StudentGroup studentGroup, HttpSession session, Model model) throws IOException {
        MultipartFile dutyFile = studentGroup.getStudentGroupReportOneFileName();
        if (!dutyFile.isEmpty()) {
            //上传文件路径
            String path = "D:\\GitHub\\managesystemapp\\src\\main\\resources\\files";
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
            int studentGroupNumber = MYUtil.getStudent(session).getStudentGroupNumber();
            studentRepository.studentGroupReportFileOneSave(studentGroupNumber, fileNewName);
        }
        return "redirect:/before/student/toMyDutyProcess?studentId=" + MYUtil.getStudent(session).getStudentId();
    }
    @Override
    public String uploadReportFileTwo(StudentGroup studentGroup, HttpSession session, Model model) throws IOException {
        MultipartFile dutyFile = studentGroup.getStudentGroupReportTwoFileName();
        if (!dutyFile.isEmpty()) {
            //上传文件路径
            String path = "D:\\GitHub\\managesystemapp\\src\\main\\resources\\files";
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
            int studentGroupNumber = MYUtil.getStudent(session).getStudentGroupNumber();
            studentRepository.studentGroupReportFileTwoSave(studentGroupNumber, fileNewName);
        }
        return "redirect:/before/student/toMyDutyProcess?studentId=" + MYUtil.getStudent(session).getStudentId();
    }
    @Override
    public String uploadReportFileThree(StudentGroup studentGroup, HttpSession session, Model model) throws IOException {
        MultipartFile dutyFile = studentGroup.getStudentGroupReportThreeFileName();
        if (!dutyFile.isEmpty()) {
            //上传文件路径
            String path = "D:\\GitHub\\managesystemapp\\src\\main\\resources\\files";
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
            int studentGroupNumber = MYUtil.getStudent(session).getStudentGroupNumber();
            studentRepository.studentGroupReportFileThreeSave(studentGroupNumber, fileNewName);
        }
        return "forward:/before/student/toMyDutyProcess?studentId=" + MYUtil.getStudent(session).getStudentId();
    }
    public StudentGroup getStudentGroupInfoByStudentNumber(Integer studentGroupNumber) {
        return teacherRepository.getStudentGroupInfoByStudentNumber(studentGroupNumber);
    }
    public String commonSet(StudentGroup studentGroup, HttpSession session, Model model) {
        studentRepository.saveStudentGroupCommon(studentGroup);
        return "forward:/before/student/toMyDutyProcess?studentId=" + MYUtil.getStudent(session).getStudentId();
    }
}
