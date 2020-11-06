package com.managesystem.managesystempackage.controller.before;

import com.managesystem.managesystempackage.entity.BUser;
import com.managesystem.managesystempackage.service.before.BeforeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/before")
public class BeforeController {
    @Autowired
    private BeforeService beforeService;
    @RequestMapping("/toLogin")
    public String toLogin(@ModelAttribute("bUser") BUser bUser) {
        return "before/login";
    }
    @RequestMapping("/login")
    public String login(@ModelAttribute("bUser") BUser bUser, HttpSession session, Model model) {
        return beforeService.login(bUser, session, model);
    }
    @RequestMapping("/toRegister")
    public String toRegister(@ModelAttribute("bUser") BUser bUser) {
        return "before/register";
    }
    @RequestMapping("/register")
    public void register(@ModelAttribute("bUser") BUser bUser, Model model) {
        beforeService.saveUser(bUser, model);
    }
}
