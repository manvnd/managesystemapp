package com.managesystem.managesystempackage.repository.before.student;

import java.util.List;

import com.managesystem.managesystempackage.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentRepository {
    List<Student> studentLogin(Student student);
    void studentSave(Student student);
}
