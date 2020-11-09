package com.managesystem.managesystempackage.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import com.managesystem.managesystempackage.entity.Student;

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
        Student student = (Student)session.getAttribute("student");
        return student;
    }
}
