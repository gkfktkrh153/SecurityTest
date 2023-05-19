package com.example.securitytest.service;


import com.example.securitytest.entity.Member;
import com.example.securitytest.entity.dto.MemberDto;
import com.example.securitytest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;
    public Member login(MemberDto memberDto){
        Member member = memberRepository.findByUsername(memberDto.getUsername()).orElse(null);
        if(member == null || !member.getPassword().equals(memberDto.getPassword()))
            return null;



        return member;
    }

}
