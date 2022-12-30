package ru.mikhelson.springcourse.SpringApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController
{
    @GetMapping("/login")
    public String loginPage()
    {
        System.out.println("Backend");
        return "auth/login";
    }



}
