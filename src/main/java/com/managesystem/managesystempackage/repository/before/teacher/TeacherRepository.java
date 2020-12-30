package com.managesystem.managesystempackage.repository.before.teacher;

import com.managesystem.managesystempackage.entity.Duty;
import com.managesystem.managesystempackage.entity.StudentGroup;
import com.managesystem.managesystempackage.entity.StudentGroupMember;
import com.managesystem.managesystempackage.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TeacherRepository {
    List<Teacher> teacherLogin(Teacher teacher);
    int teacherSave(Teacher teacher);
    int dutyAdd(Duty duty);
    List<Duty> getDutyByTeacherIdByPage(Integer teacherId, int startIndex, int perPageSize);
    int getAllDutyByTeacherId(Integer teacherId);
    List<Duty> getDutyByPage(int startIndex, int perPageSize);
    int getAllDuty();
    StudentGroup getStudentGroupProcessByDutyId(Integer dutyId);
    int checkDutyByTeacherId(Integer teacherId);
    Duty selectOneDuty(Integer dutyId);
    int updateDutyByDutyConcludeFile(Duty duty);
    int updateDutyByDutyExceptFile(Duty duty);
    List<StudentGroupMember> getStudentNameAndIdByStudentGroupNumber(Integer studentGroupNumber);
    int setLeaderByStudentId(Integer studentId, Integer studentGroupNumber);
    int updateGrade(StudentGroup studentGroup);
    StudentGroup getStudentGroupInfoByStudentNumber(Integer studentGroupNumber);
    String checkFirstReport(Integer studentGroupNumber);
    String checkSecondReport(Integer studentGroupNumber);
    String checkThirdReport(Integer studentGroupNumber);
}
