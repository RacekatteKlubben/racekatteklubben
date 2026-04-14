package com.example.racekatteklubben.controller;

import com.example.racekatteklubben.domain.Cat;
import com.example.racekatteklubben.domain.Member;
import com.example.racekatteklubben.service.CatService;
import com.example.racekatteklubben.service.validation.ValidationExceptionCat;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/createCat")
    public String createCat(Model model) {
        model.addAttribute("cat", new Cat());
        return "createCat";
    }

    @PostMapping("/createCat")
    public String createCat(@ModelAttribute Cat cat, HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");

        if (member == null){
            return "redirect:/login";
        }

        try {
            catService.createCat(cat, member.getMemberId());
            model.addAttribute("cats", catService.findCatsByMemberId(member.getMemberId()));
            return "catPage";
        } catch (ValidationExceptionCat ec){
            model.addAttribute("error", ec.getMessage());
            return "createCat";
        }
    }

    @GetMapping("/catPage")
    public String catPage(HttpSession session, Model model) {

        Member member = (Member) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login";
        }

        model.addAttribute("cats", catService.findCatsByMemberId(member.getMemberId()));
        return "catPage";
    }
}
