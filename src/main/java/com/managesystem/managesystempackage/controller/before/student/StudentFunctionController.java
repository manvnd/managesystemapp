package com.managesystem.managesystempackage.controller.before.student;

import com.managesystem.managesystempackage.entity.Duty;
import com.managesystem.managesystempackage.entity.StudentGroup;
import com.managesystem.managesystempackage.service.before.student.StudentService;
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
@RequestMapping("/before/student")
public class StudentFunctionController extends StudentBaseController{
    @Autowired
    private StudentService studentService;
    @RequestMapping("/home")
    public String home(Model model, HttpSession session) {
        return studentService.home(model, session);
    }
    @RequestMapping("/toDutyInfo")
    public String toDutyInfo(Model model, Integer currentPage) {
        return studentService.getDutyList(model, currentPage);
    }
    @RequestMapping("/dutyChoose")
    public String dutyChoose(Integer dutyId, Integer studentId, HttpSession session) {
        return studentService.dutyChoose(dutyId, studentId, session);
    }
    @RequestMapping("/toMyStudentGroup")
    public String toMyStudentGroup(Model model, Integer studentId) {
        return studentService.studentGroupSelectByStudentId(model, studentId);
    }
    @RequestMapping("/toMyDutyProcess")
    public String toMyDutyProcess(Model model, Integer studentId) {
        return studentService.studentGroupDutySelectByStudentId(model, studentId);
    }
//    @RequestMapping("studentGroupReportFileUpload")
//    public String dutyAdd(HttpServletRequest request, Model model, HttpSession session) throws IOException {
//
//    }
    @RequestMapping("/toUploadReportFile")
    public String toUploadReportFile(@ModelAttribute("studentGroup") StudentGroup studentGroup, Model model, String fileName) {
        return studentService.toUploadReportFile(studentGroup, model, fileName);
    }
    @RequestMapping("uploadReportFileOne")
    public String uploadReportFileOne(@ModelAttribute("studentGroup") StudentGroup studentGroup, HttpSession session, Model model) throws IOException {
        return studentService.uploadReportFileOne(studentGroup, session, model);
    }
    @RequestMapping("uploadReportFileTwo")
    public String uploadReportFileTwo(@ModelAttribute("studentGroup") StudentGroup studentGroup, HttpSession session, Model model) throws IOException {
        return studentService.uploadReportFileTwo(studentGroup, session, model);
    }
    @RequestMapping("uploadReportFileThree")
    public String uploadReportFileThree(@ModelAttribute("studentGroup") StudentGroup studentGroup, HttpSession session, Model model) throws IOException {
        return studentService.uploadReportFileThree(studentGroup, session, model);
    }
    @RequestMapping("/fileDownload")
    public ResponseEntity<byte[]> fileDownload(String fileName, @RequestHeader("User-Agent") String userAgent) throws IOException {
        //下载文件路径
        String path = "/Users/yucan/Downloads/Code/04/Project/managesystemapp/src/main/resources/files";
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
}
