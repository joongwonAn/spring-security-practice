package com.springboot.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auths")
public class AuthController {
    @GetMapping("/login-form")
    public String loginForm() {
        return "login";
    }


    @PostMapping("/login")
    public String login() {
        // TODO 아이디, 패스워드 받아서 인증 처리를 해야 한다.
        System.out.println("Login successfully!");
        return "home";
    }
}
