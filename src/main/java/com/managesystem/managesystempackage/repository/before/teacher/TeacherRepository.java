package com.managesystem.managesystempackage.repository.before.teacher;

import com.managesystem.managesystempackage.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TeacherRepository {
    List<Teacher> teacherLogin(Teacher teacher);
    int teacherSave(Teacher teacher);
}
