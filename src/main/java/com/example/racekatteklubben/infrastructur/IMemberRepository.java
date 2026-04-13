package com.example.racekatteklubben.infrastructur;

import com.example.racekatteklubben.domain.Member;

public interface IMemberRepository {
    Member findMemberByEmail(String email);
    void updateMember(Member member);
    void createMember(Member member);
}
