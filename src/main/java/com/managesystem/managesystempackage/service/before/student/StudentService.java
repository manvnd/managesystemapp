package com.managesystem.managesystempackage.service.before.student;

import com.managesystem.managesystempackage.entity.Duty;
import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.entity.StudentGroup;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface StudentService {
    public String studentLogin(Student student, HttpSession session, Model model);
    public String studentSave(Student student, Model model);
    public String home(Model model, HttpSession session);
    public String getDutyList(Model model, HttpSession session, Integer currentPage);
    public String dutyChoose(Integer dytyId, HttpSession session);
    public String studentGroupSelectByStudentId(Model model, Integer studentId);
    public String studentGroupDutySelectByStudentId(Model model, Integer studentId);
    public String toUploadReportFile(Integer studentGroupNumber, HttpSession session, Model model, String fileName);
    public String uploadReportFileOne(StudentGroup studentGroup, HttpSession session, Model model) throws IOException;
    public String uploadReportFileTwo(StudentGroup studentGroup, HttpSession session, Model model) throws IOException;
    public String uploadReportFileThree(StudentGroup studentGroup, HttpSession session, Model model) throws IOException;
    public StudentGroup getStudentGroupInfoByStudentNumber(Integer studentGroupNumber);
    public String commonSet(StudentGroup studentGroup, HttpSession session, Model model);
}
