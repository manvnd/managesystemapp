package com.managesystem.managesystempackage.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class Teacher {
    private Integer id;
    @NotBlank(message = "姓名必须输入！")
    private String teacherName;
    @NotBlank(message = "教工号必须输入！")
    private String teacherNumber;
    @NotBlank(message="密码必须输入！")
    @Length(min=6, max=20, message="密码长度在6到20之间！")
    private String teacherPwd;
    private String teachaerRepwd;
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherPwd() {
        return teacherPwd;
    }

    public void setTeacherPwd(String teacherPwd) {
        this.teacherPwd = teacherPwd;
    }

    public String getTeachaerRepwd() {
        return teachaerRepwd;
    }

    public void setTeachaerRepwd(String teachaerRepwd) {
        this.teachaerRepwd = teachaerRepwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
