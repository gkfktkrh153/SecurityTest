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
    private final MemberService memberService;
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        // 세션이 존재한다면 세션에 저장되있는 member조회

        if(session == null) { // 아직 인증 X
            return "login";
        }
        Member loginMember = (Member) session.getAttribute("LOGIN_MEMBER");
        model.addAttribute("member", loginMember);
        return "home";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpServletRequest request){
        Member loginMember = memberService.login(memberDto);

        if(loginMember == null) // 로그인 실패
            return "redirect:/"; // 세션없이 홈으로 이동 -> 로그인 페이지 이동

        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_MEMBER", loginMember);

        return "redirect:/"; // 세션이 존재하므로 home으로 이동
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) // 세션이 존재한다면
            session.invalidate(); // 세션 만료
        return "redirect:/";
    }
}
