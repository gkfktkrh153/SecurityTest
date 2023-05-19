package com.example.securitytest.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/hello")
    public String hello(HttpServletRequest request){

        return "hello";
    }
}
