package com.managesystem.managesystempackage.entity;

public class Student {
    private Integer id;
    private String studentName;
    private String studentNumber;
    private String studentPwd;
    private String studentRebpwd;
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStudentRebpwd() {
        return studentRebpwd;
    }

    public void setStudentRebpwd(String studentRebpwd) {
        this.studentRebpwd = studentRebpwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
