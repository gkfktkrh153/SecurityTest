package com.example.securitytest.controller;

import com.example.securitytest.entity.Member;
import com.example.securitytest.entity.dto.MemberDto;
import com.example.securitytest.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final SessionRegistry sessionRegistry;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model, HttpSession session) {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        // 각 Principal(사용자)에 대한 세션 정보를 가져옵니다.
        for (Object principal : allPrincipals) {
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                // 현재 사용자의 모든 세션 정보를 가져옵니다.
                List<SessionInformation> sessions = sessionRegistry.getAllSessions(userDetails, false);
                sessions.stream().forEach(s-> log.info(userDetails.getUsername()+" : "+ s.getSessionId()));
            }
        }
        return "home";
    }
    @GetMapping("/login")
    public String login_form(HttpServletRequest request, Model model) {
        return "login";
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
