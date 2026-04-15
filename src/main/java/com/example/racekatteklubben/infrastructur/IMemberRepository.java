package com.example.racekatteklubben.infrastructur;

import com.example.racekatteklubben.domain.Member;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IMemberRepository {
    Member findMemberByEmail(String email);
    void updateMember(Member member);
    void createMember(Member member);
    List<Member> memberSearch(String query);
    List<Member> findAllMembers();
    void deleteMember(int memberId);
}
