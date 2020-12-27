package com.managesystem.managesystempackage.entity;

public class StudentGroupMember {
    private int studentMemberId;
    private int studentGroupId;
    private int studentId;
    private boolean leader;

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    private String studentName;


    public int getStudentMemberId() {
        return studentMemberId;
    }

    public void setStudentMemberId(int studentMemberId) {
        this.studentMemberId = studentMemberId;
    }

    public int getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(int studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

}
