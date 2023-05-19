package com.example.securitytest.controller;

import com.example.securitytest.SessionManager;
import com.example.securitytest.entity.Member;
import com.example.securitytest.entity.dto.MemberDto;
import com.example.securitytest.repository.MemberRepository;
import com.example.securitytest.service.MemberService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final SessionManager sessionManager;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        String username = sessionManager.getSession(request);
        // 세션이 존재한다면 세션에 저장되있는 member의 username조회

        if(username == null) { // 아직 인증 X
            return "login";
        }

        Member member = memberRepository.findByUsername(username).orElse(null);

        if(member == null) { // 아이디가 삭제된경우!?
            return "login";
        }

        model.addAttribute("member", member);
        return "home";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpServletResponse response){
        Member loginMember = memberService.login(memberDto);

        if(loginMember == null) // 로그인 실패
            return "redirect:/"; // 세션없이 홈으로 이동 -> 로그인 페이지 이동

        sessionManager.createSession(loginMember.getUsername(), response); // 세션 생성

        return "redirect:/";
    }
}
