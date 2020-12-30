package com.managesystem.managesystempackage.controller.before.teacher;

import com.managesystem.managesystempackage.entity.Duty;
import com.managesystem.managesystempackage.entity.StudentGroup;
import com.managesystem.managesystempackage.entity.Teacher;
import com.managesystem.managesystempackage.service.before.teacher.TeacherService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/before/teacher")
public class TeacherFunctionController extends TeacherBaseController {
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/home")
    public String home() {
        return "before/teacher/home";
    }
    @RequestMapping("/toDutyAdd")
    public String toDutyAdd(@ModelAttribute("duty") Duty duty) {
        return "/before/teacher/dutyAdd";
    }
    @RequestMapping("dutyAdd")
    public String dutyAdd(@ModelAttribute("duty") Duty duty, HttpSession session, Model model) throws IOException {
        return teacherService.dutyAdd(duty, session, model);
    }
    @RequestMapping("/toDutyInfo")
    public String toDutyInfo(Model model, Integer teacherId, Integer currentPage) {
        return teacherService.toDutyInfo(model, teacherId, currentPage);
    }

    @RequestMapping("/fileDownload")
    public ResponseEntity<byte[]> fileDownload(String fileName, @RequestHeader("User-Agent") String userAgent) throws IOException {
        //下载文件路径
        String path = "C:\\Users\\WangTian\\Desktop\\managesystemapp\\src\\main\\resources\\files";
        //构建将要下载的文件对象
        File downFile = new File(path + File.separator + fileName);
        //ok表示HTTP中的状态为200
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
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
    @RequestMapping("/toCheckStudentGroupProcess")
    public String toCheckStudentGroupProcess(Model model, Integer dutyId, HttpSession session) {
        return teacherService.toCheckStudentGroupProcess(model, dutyId, session);
    }
    @RequestMapping("/toUpdateDuty")
    public String toUpdateDuty(Model model, Integer dutyId) {
        Duty duty = teacherService.selectOneDuty(dutyId);
        model.addAttribute("duty", duty);
        return "/before/teacher/dutyUpdate";
    }
    @RequestMapping("/updateDuty")
    public String updateDuty(@ModelAttribute("duty") Duty duty, HttpSession session) throws IOException {
        return teacherService.updateDuty(duty, session);
    }
    @RequestMapping("/toLeaderSet")
    public String toLeaderSet(Model model, Integer studentGroupNumber) {
        return teacherService.toLeaderSet(model, studentGroupNumber);
    }
    @RequestMapping("/leaderSet")
    public String leaderSet(Model model, HttpSession session, Integer studentId) {
        return teacherService.leaderSet(model, session, studentId);
    }
    @RequestMapping("/toSetGrade")
    public String toSetGrade(Model model, Integer studentGroupNumber) {
        if (!teacherService.checkFirstReport(studentGroupNumber) && !teacherService.checkSecondReport(studentGroupNumber) && !teacherService.checkThirdReport(studentGroupNumber)) {
            StudentGroup studentGroup = teacherService.getStudentGroupInfoByStudentNumber(studentGroupNumber);
            model.addAttribute("studentGroup", studentGroup);
            return "/before/teacher/setGrade";
        }
        model.addAttribute("message", "学生还未完成课程");
        return "forward:/before/teacher/toCheckStudentGroupProcess?dutyId=" + studentGroupNumber;
    }
    @RequestMapping("/gradeSet")
    public String gradeSet(@ModelAttribute("studentGroup") StudentGroup studentGroup, Model model) {
        return teacherService.gradeSet(studentGroup, model);
    }


}
