package com.example.racekatteklubben.controller;

import com.example.racekatteklubben.domain.Member;
import com.example.racekatteklubben.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
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

    @PostMapping("/login")
    public String login(@ModelAttribute("member") Member loginForm, Model model, HttpSession session) {
        Member dbMember = memberService.login(loginForm);

        session.setAttribute("member", dbMember);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model){
        model.addAttribute("loginForm", new Member());
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
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
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return "createMember";
    }


    @GetMapping("/homePageLoginTrue")
    public String homePageLoginTrue(Model model) {
        return "homePageLoginTrue";
    }

    @GetMapping("/updateMember")
    public String ShowUpdateMemberPage(@ModelAttribute("member")Member member, Model model) {
        model.addAttribute("member", member);
        return "updateMember";
    }




}
