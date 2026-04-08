package com.example.racekatteklubben.controller;

import com.example.racekatteklubben.domain.Cat;
import com.example.racekatteklubben.service.CatService;
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

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/createCat")
    public String createCat(Model model) {
        model.addAttribute("cat", new Cat());
        return "createCat";
    }

    @PostMapping("/createCat")
    public String createCat(@ModelAttribute Cat cat, Model model) {
        try {
            catService.createCat(cat);
            model.addAttribute("cat", cat);
            return "redirect:/catPage";
        } catch (Exception ex){
            System.out.println("Error"+ ex.getMessage());
        }
        return "createCat";
    }

    @GetMapping("/catPage")
    public String catPage(Model model) {
        model.addAttribute("cats", catService.findAllCats());
        return "catPage";
    }
}
