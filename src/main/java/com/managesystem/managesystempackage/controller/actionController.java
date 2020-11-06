package com.managesystem.managesystempackage.controller;

import com.managesystem.managesystempackage.entity.BUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class actionController {
    @RequestMapping("/")
    public String toLogin(@ModelAttribute("bUser") BUser bUser) {
        return "before/login";
    }
}
