package com.example.racekatteklubben.controller;

import com.example.racekatteklubben.domain.Cat;
import com.example.racekatteklubben.domain.Member;
import com.example.racekatteklubben.service.CatService;
import com.example.racekatteklubben.service.MemberService;
import com.example.racekatteklubben.service.validation.ValidationExceptionMember;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final CatService catService;

    public MemberController(MemberService memberService, CatService catService) {
        this.memberService = memberService;
        this.catService = catService;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");

        if (member != null && member.isCurrentLogin()) {
            model.addAttribute("member", member);
            return "homePageLoginTrue";
        }
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model){
        model.addAttribute("loginForm", new Member());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") Member loginForm, Model model, HttpSession session) {
        try {
            Member dbMember = memberService.login(loginForm);
            session.setAttribute("member", dbMember);
            return "redirect:/";
        } catch (ValidationExceptionMember em){
            model.addAttribute("error", em.getMessage());
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, Model model){
        try {
            session.invalidate();
            return "redirect:/";
        } catch (ValidationExceptionMember em){
            model.addAttribute("error", em.getMessage());
            return "login";
        }
    }

    @GetMapping("/createMember")
    public String createMember(Model model) {
        model.addAttribute("member", new Member());
        return "createMember";
    }

    @PostMapping("/createMember")
    public String createMember(@ModelAttribute Member member, Model model) {
        try {
            memberService.createMember(member);
            return "redirect:/";
        } catch (ValidationExceptionMember em){
            model.addAttribute("error", em.getMessage());
        }
        return "createMember";
    }

    @GetMapping("/homePageLoginTrue")
    public String homePageLoginTrue(Model model) {
        return "homePageLoginTrue";
    }

    @GetMapping("/updateMember")
    public String showUpdateMemberPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");

        model.addAttribute("member", member);
        return "updateMember";
    }

    @PostMapping("/updateMember")
    public String updateMember(@ModelAttribute("member") Member updatedMember,
                               HttpSession session,
                               Model model) {
        try {
            Member sessionMember = (Member) session.getAttribute("member");

            updatedMember.setMemberId(sessionMember.getMemberId());
            updatedMember.setCurrentLogin(true);

            memberService.updateMember(updatedMember);
            session.setAttribute("member", updatedMember);

            return "redirect:/";
        } catch (ValidationExceptionMember em) {
            model.addAttribute("errorMessage", em.getMessage());
            return "updateMember";
        }
    }

    @PostMapping("/deleteMember")
    public String deleteMember(@ModelAttribute("member") Member updatedMember ,HttpSession session, Model model) {
        try {
            Member sessionMember = (Member) session.getAttribute("member");
            memberService.deleteMember(sessionMember.getMemberId());
            session.invalidate();

            return "redirect:/login";
        } catch (ValidationExceptionMember em) {
            model.addAttribute("error", em.getMessage());
            return "profile";
        }
    }

    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model) {
        Member currentMember = (Member) session.getAttribute("member");
        if (currentMember == null) {
            return "redirect:/login";
        }

        model.addAttribute("cats", catService.findCatsByMemberId(currentMember.getMemberId()));
        return "profile";
    }

   @GetMapping("/memberSearch")
    public String searchMembers(@RequestParam("query") String query, Model model, HttpSession session) {
        Member loggedInMember = (Member) session.getAttribute("member");
        if (loggedInMember == null) {
            return "redirect:/login";
        }
        model.addAttribute("member", loggedInMember);
        List<Member> results = memberService.memberSearch(query);
        for (Member member : results) {
            List<Cat> cats = catService.findCatsByMemberId(member.getMemberId());
            member.setCats(cats);
        }
        model.addAttribute("results", results);
        return "homePageLoginTrue";
    }

    @GetMapping("/allMembers")
    public String showAllMembers(Model model, HttpSession session) {
        Member loggedInMember = (Member) session.getAttribute("member");
        if (loggedInMember == null) {
            return "redirect:/login";
        }

        List<Member> allMembers = memberService.findAllMembers();

        model.addAttribute("member", loggedInMember);
        model.addAttribute("allMembers", allMembers);
        return "allMembers";
    }



}
