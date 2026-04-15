package com.example.racekatteklubben.service;

import com.example.racekatteklubben.domain.Member;
import com.example.racekatteklubben.infrastructur.IMemberRepository;
import com.example.racekatteklubben.service.validation.Validation;
import com.example.racekatteklubben.service.validation.ValidationExceptionMember;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final IMemberRepository iMemberRepository;
    private final Validation validate;
    private final PasswordService passwordService;

    public MemberService(IMemberRepository iMemberRepository, Validation validate, PasswordService passwordService) {
        this.iMemberRepository = iMemberRepository;
        this.validate = validate;
        this.passwordService = passwordService;
    }

    //Oprettelse af user og opdatering af member
    public void createMember(Member member){
        validate.validateMember(member);
        try {
            member.setPassword(passwordService.hash(member.getPassword()));
            iMemberRepository.createMember(member);
        } catch (ValidationExceptionMember ex) {
            throw new ValidationExceptionMember("Fejl ved oprettelse af medlemskab");
        }
    }
    public void updateMember(Member member) {
        validate.validateMember(member);
        try {
            String password = member.getPassword();
            if (!password.startsWith("$2")) {
                member.setPassword(passwordService.hash(password));
            }
            iMemberRepository.updateMember(member);
        } catch (ValidationExceptionMember ex) {
            throw new ValidationExceptionMember("Fejl ved opdatering af din profil");
        }
    }

    public Member login(Member loginMember){
        validate.validateMemberLogin(loginMember);
        Member dbMember;
        try {
            dbMember = iMemberRepository.findMemberByEmail(loginMember.getEmail());
        } catch (EmptyResultDataAccessException | ValidationExceptionMember dae) {
            throw new ValidationExceptionMember("Forkert Email eller Password");
        }

        if (!passwordService.matches(loginMember.getPassword(), dbMember.getPassword())) {
            throw new ValidationExceptionMember("Forkert Email eller Password");
        }

        dbMember.setCurrentLogin(true);
        return dbMember;
    }

    public List<Member> memberSearch(String query){
        return iMemberRepository.memberSearch(query);
    }

    public void deleteMember(int memberId){
        try {
            iMemberRepository.deleteMember(memberId);
        } catch (Exception ex) {
            throw new ValidationExceptionMember("Fejl ved sletning af bruger");
        }
    }


    public List<Member> findAllMembers() {
        return iMemberRepository.findAllMembers();
    }


}
