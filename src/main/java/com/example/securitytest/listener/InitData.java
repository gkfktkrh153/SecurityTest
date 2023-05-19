package com.example.securitytest.listener;

import com.example.securitytest.entity.Member;
import com.example.securitytest.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Component
public class InitData implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        setUpDataLoad();
    }
    @Transactional
    public void setUpDataLoad(){
        memberRepository.save(new Member("user","1234"));
    }
}

