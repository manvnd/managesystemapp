package com.managesystem.managesystempackage.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import com.managesystem.managesystempackage.entity.Student;
import com.managesystem.managesystempackage.entity.Teacher;

public class MYUtil {
    //将实际的文件名重命名
    public static String getNewFileName(String oldFileName) {
        int lastIndex = oldFileName.lastIndexOf(".");
        String fileType = oldFileName.substring(lastIndex);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmssSSS");
        String time = sdf.format(now);
        String newFileName = time+fileType;
        return newFileName;
    }
    //获得用户信息
    public static Student getStudent(HttpSession session) {
        return (Student)session.getAttribute("student");
    }
    //获取教师信息
    public static Teacher getTeacher(HttpSession session) {
        return (Teacher)session.getAttribute("teacher");
    }
}
