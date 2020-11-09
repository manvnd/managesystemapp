package com.managesystem.managesystempackage.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class Student {
    private Integer id;
    @NotBlank(message = "姓名必须输入！")
    private String studentName;
    @NotBlank(message = "学号必须输入！")
    private String studentNumber;
    @NotBlank(message="密码必须输入！")
    @Length(min=6, max=20, message="密码长度在6到20之间！")
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
