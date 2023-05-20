package com.example.securitytest;

import com.example.securitytest.entity.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionManager {
    private String SESSION_ID_NAME = "JSESSION_ID";
    private final HashMap<String, Session> sessionStore;

    public Session createSession(Member value, HttpServletResponse response)
    {
        String token = UUID.randomUUID().toString(); // sessionId 생성
        Session session = new Session();
        sessionStore.put(token, session); // 세션에 데이터 저장
        Cookie cookie = new Cookie(SESSION_ID_NAME, token); // LOGIN_MEMBER라는 이름을 가지고 세션아이디를 담은 쿠키
        response.addCookie(cookie); // 쿠키 response에 담기

        return session;
    }
    public Session getSession(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request);
        if(sessionCookie == null)
            return null;

        return sessionStore.get(sessionCookie.getValue());
    }
    public Cookie findCookie(HttpServletRequest request){
        if(request.getCookies() == null)
            return null;

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(SESSION_ID_NAME)) // 세션아이디를 담은 쿠키 조회
                .findFirst()
                .orElse(null);
    }

    public void sessionExpire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request);
        if(sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }
}
