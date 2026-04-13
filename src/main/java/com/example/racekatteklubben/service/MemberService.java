package com.example.racekatteklubben.service;

import com.example.racekatteklubben.domain.Member;
import com.example.racekatteklubben.infrastructur.IMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final IMemberRepository iMemberRepository;

    public MemberService(IMemberRepository iMemberRepository) {
        this.iMemberRepository = iMemberRepository;
    }

    public void createMember(Member member){
        iMemberRepository.createMember(member);
    }
    public  void updateMember(Member member){
        iMemberRepository.updateMember(member);
    }

    public Member login(Member loginMember){
        Member dbMember = iMemberRepository.findMemberByEmail(loginMember.getEmail());
        if (!dbMember.getPassword().equals(loginMember.getPassword())) {
            throw new RuntimeException("Der er sket en kritisk fejl");
        }

        dbMember.setCurrentLogin(true);
        return dbMember;
    }
}
