package com.yk.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    // add a req mapping for/leaders
    @GetMapping("/leaders")
    public String showLeaders() {
        return "leaders";
    }

    // add a req mapping for/admins
    @GetMapping("/admins")
    public String showAdmins() {
        return "admins";
    }
}
