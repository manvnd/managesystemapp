package com.managesystem.managesystempackage.service.before.teacher;

import com.managesystem.managesystempackage.entity.Duty;
import com.managesystem.managesystempackage.entity.StudentGroup;
import com.managesystem.managesystempackage.entity.Teacher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface TeacherService {
    public String teacherLogin(Teacher teacher, HttpSession session, Model model);
    public String teacherSave(Teacher teacher, Model model);
    public String dutyAdd(Duty duty, HttpSession session, Model model) throws IOException;
    public String toDutyInfo(Model model, Integer teacherId, Integer currentPage);
    public String toCheckStudentGroupProcess(Model model, Integer dutyId, HttpSession session);
    public Duty selectOneDuty(Integer dutyId);
    public String updateDuty(Duty duty, HttpSession session) throws IOException;
    public String toLeaderSet(Model model, Integer studentGroupNumber);
    public String leaderSet(Model model, HttpSession session, Integer studentId);
    public StudentGroup getStudentGroupInfoByStudentNumber(Integer studentGroupNumber);
    public String gradeSet(StudentGroup studentGroup, Model model);
    public boolean checkFirstReport(Integer studentGroupNumber);
    public boolean checkSecondReport(Integer studentGroupNumber);
    public boolean checkThirdReport(Integer studentGroupNumber);
}
