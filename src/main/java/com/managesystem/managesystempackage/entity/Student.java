package com.managesystem.managesystempackage.entity;

public class Student {
    private Integer studentId;
    private String studentName;
    private String studentNumber;
    private String studentPwd;
    private int studentGroupNumber;
    private String isLeader;

    public int getStudentGroupNumber() {
        return studentGroupNumber;
    }

    public void setStudentGroupNumber(int studentGroupNumber) {
        this.studentGroupNumber = studentGroupNumber;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentPwd() {
        return studentPwd;
    }

    public void setStudentPwd(String studentPwd) {
        this.studentPwd = studentPwd;
    }

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader;
    }
}
