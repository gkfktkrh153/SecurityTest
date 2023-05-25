package com.example.securitytest.controller;

import com.example.securitytest.entity.Member;
import com.example.securitytest.entity.dto.MemberDto;
import com.example.securitytest.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {


        if(session == null) { // 아직 인증 X
            return "login";
        }

        Member loginMember = (Member) session.getAttribute("LOGIN_MEMBER");
        model.addAttribute("member", loginMember);
        return "home";
    }

}
