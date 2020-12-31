package com.managesystem.managesystempackage.repository.before.student;

import java.util.List;

import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.entity.StudentGroup;
import com.managesystem.managesystempackage.entity.StudentGroupMember;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentRepository {
    List<Student> studentLogin(Student student);
    Integer studentSave(Student student);
    Integer studentGroupInsert(Integer dutyId, Integer studentId, Integer dutySelectedStudentNumber, String studentName);
    Integer getDutySelectedStudentNumber(Integer dutyId);
    Integer getStudentGroupNumberByStudentId(Integer studentId);
    Integer checkStudentGroupNumberByStudentId(Integer studentId);
    List<StudentGroupMember> getStudentGroupByStudentGroupNumber(Integer studentId);
    StudentGroup getStudentGroupDutyByStudentGroupNumber(Integer studentId);
    Integer studentGroupReportFileOneSave(Integer studentGroupNumber, String fileName);
    Integer studentGroupReportFileTwoSave(Integer studentGroupNumber, String fileName);
    Integer studentGroupReportFileThreeSave(Integer studentGroupNumber, String fileName);
    Integer checkStudentNumber(String studentNumber);
    Integer checkStudentSelectedDuty(Integer studentId);
    String checkStudentLeader(Integer studentId);
    Integer saveStudentGroupCommon(StudentGroup studentGroup);
    Integer getDutyIdByStudentGroupNumber(Integer studentGroupNumber);
}
