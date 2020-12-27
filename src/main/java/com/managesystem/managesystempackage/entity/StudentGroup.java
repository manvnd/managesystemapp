package com.managesystem.managesystempackage.entity;

import org.springframework.web.multipart.MultipartFile;

public class StudentGroup {
    private int studentGroupId;
    private int studentGroupNumber;
    private int leaderStudentId;
    private int dutyId;
    private String studentGroupReportOneFile;
    private String studentGroupReportTwoFile;
    private String studentGroupReportThreeFile;
    private float studentGroupScore;
    private String studentGroupCommons;
    private MultipartFile studentGroupReportOneFileName;
    private MultipartFile studentGroupReportTwoFileName;
    private MultipartFile studentGroupReportThreeFileName;
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(int studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public int getStudentGroupNumber() {
        return studentGroupNumber;
    }

    public int getLeaderStudentId() {
        return leaderStudentId;
    }

    public void setLeaderStudentId(int leaderStudentId) {
        this.leaderStudentId = leaderStudentId;
    }

    public void setStudentGroupNumber(int studentGroupNumber) {
        this.studentGroupNumber = studentGroupNumber;
    }

    public int getDutyId() {
        return dutyId;
    }

    public void setDutyId(int dutyId) {
        this.dutyId = dutyId;
    }

    public String getStudentGroupReportOneFile() {
        return studentGroupReportOneFile;
    }

    public void setStudentGroupReportOneFile(String studentGroupReportOneFile) {
        this.studentGroupReportOneFile = studentGroupReportOneFile;
    }

    public String getStudentGroupReportTwoFile() {
        return studentGroupReportTwoFile;
    }

    public void setStudentGroupReportTwoFile(String studentGroupReportTwoFile) {
        this.studentGroupReportTwoFile = studentGroupReportTwoFile;
    }

    public String getStudentGroupReportThreeFile() {
        return studentGroupReportThreeFile;
    }

    public void setStudentGroupReportThreeFile(String studentGroupReportThreeFile) {
        this.studentGroupReportThreeFile = studentGroupReportThreeFile;
    }

    public float getStudentGroupScore() {
        return studentGroupScore;
    }

    public void setStudentGroupScore(float studentGroupScore) {
        this.studentGroupScore = studentGroupScore;
    }

    public String getStudentGroupCommons() {
        return studentGroupCommons;
    }

    public void setStudentGroupCommons(String studentGroupCommons) {
        this.studentGroupCommons = studentGroupCommons;
    }

    public MultipartFile getStudentGroupReportOneFileName() {
        return studentGroupReportOneFileName;
    }

    public void setStudentGroupReportOneFileName(MultipartFile studentGroupReportOneFileName) {
        this.studentGroupReportOneFileName = studentGroupReportOneFileName;
    }

    public MultipartFile getStudentGroupReportTwoFileName() {
        return studentGroupReportTwoFileName;
    }

    public void setStudentGroupReportTwoFileName(MultipartFile studentGroupReportTwoFileName) {
        this.studentGroupReportTwoFileName = studentGroupReportTwoFileName;
    }

    public MultipartFile getStudentGroupReportThreeFileName() {
        return studentGroupReportThreeFileName;
    }

    public void setStudentGroupReportThreeFileName(MultipartFile studentGroupReportThreeFileName) {
        this.studentGroupReportThreeFileName = studentGroupReportThreeFileName;
    }


}
