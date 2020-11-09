package com.managesystem.managesystempackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class actionController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
