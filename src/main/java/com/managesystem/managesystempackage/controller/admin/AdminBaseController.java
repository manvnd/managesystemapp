package com.managesystem.managesystempackage.controller.admin;

import com.managesystem.managesystempackage.NoLoginException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class AdminBaseController {
    //登录权限控制，处理方法执行前执行该方法
    @ModelAttribute
    public void isLogin(HttpSession session) throws NoLoginException {
        if(session.getAttribute("admin") == null) {
            throw new NoLoginException("没有登录");
        }
    }
}
