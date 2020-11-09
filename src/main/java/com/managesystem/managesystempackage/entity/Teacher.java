package com.managesystem.managesystempackage.entity;

public class Teacher {
    private Integer id;
    private String teacherName;
    private String teacherNumber;
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
