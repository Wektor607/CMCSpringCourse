package ru.mikhelson.springcourse.SpringApp.controllers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mikhelson.springcourse.SpringApp.models.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
