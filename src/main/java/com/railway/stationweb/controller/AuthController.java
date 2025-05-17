package com.railway.stationweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Повертає шаблон login.html з templates/
    }
}