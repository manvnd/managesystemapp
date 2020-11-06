package com.managesystem.managesystempackage.service.admin;

import com.managesystem.managesystempackage.entity.AUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface AdminService {
    public String login(AUser aUser, HttpSession session, Model model);
}
