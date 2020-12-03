package com.managesystem.managesystempackage.entity;

import org.springframework.web.multipart.MultipartFile;

public class Duty {
    private int dutyId;
    private String dutyName;
    private String dutySummary;
    private String dutyDescriptionFile;

    private int teacherId;
    private int dutySize;
    private int dutySelectedNumber;
    private MultipartFile dutyFileName;

    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getDutyId() {
        return dutyId;
    }

    public void setDutyId(int dutyId) {
        this.dutyId = dutyId;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutySummary() {
        return dutySummary;
    }

    public void setDutySummary(String dutySummary) {
        this.dutySummary = dutySummary;
    }

    public String getDutyDescriptionFile() {
        return dutyDescriptionFile;
    }

    public void setDutyDescriptionFile(String dutyDescriptionFile) {
        this.dutyDescriptionFile = dutyDescriptionFile;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getDutySize() {
        return dutySize;
    }

    public void setDutySize(int dutySize) {
        this.dutySize = dutySize;
    }

    public int getDutySelectedNumber() {
        return dutySelectedNumber;
    }

    public void setDutySelectedNumber(int dutySelectedNumber) {
        this.dutySelectedNumber = dutySelectedNumber;
    }

    public MultipartFile getDutyFileName() {
        return dutyFileName;
    }

    public void setDutyFileName(MultipartFile dutyFileName) {
        this.dutyFileName = dutyFileName;
    }
}
