package com.managesystem.managesystempackage.service.before;

import com.managesystem.managesystempackage.entity.BUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

public interface BeforeService {
    public String login(BUser bUser, HttpSession session, Model model);
    public void saveUser(BUser bUser, Model model);
}
