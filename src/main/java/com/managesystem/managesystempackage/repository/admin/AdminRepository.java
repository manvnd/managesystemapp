package com.managesystem.managesystempackage.repository.admin;

import java.util.List;

import com.managesystem.managesystempackage.entity.Admin;
import com.managesystem.managesystempackage.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminRepository {
    List<Admin> adminLogin(Admin admin);
    List<Student> selectStudentsByPage(int startIndex, int perPageSize);
    int selectAllStudents();
    void studentDelete(int id);
    int studentAdd(Student student);
    int studentUpdate(Student student);
    Student selectOneStudent(int id);
}
