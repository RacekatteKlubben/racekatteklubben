package com.example.racekatteklubben.controller;

import com.example.racekatteklubben.domain.Cat;
import com.example.racekatteklubben.domain.Member;
import com.example.racekatteklubben.service.CatService;
import com.example.racekatteklubben.service.validation.ValidationExceptionCat;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public String createCat(@ModelAttribute Cat cat, HttpSession session, Model model, @RequestParam ("imageFile") MultipartFile image) {
        Member member = (Member) session.getAttribute("member");

        if (member == null){
            return "redirect:/login";
        }

        //Tilføjelse af billede
        if (!image.isEmpty()) {
            try {
                byte[] bytes = image.getBytes();
                cat.setImage(bytes);
            } catch (IOException ioe){
                model.addAttribute("error", "Kunne ikke oploade billede");
                return "createCat";
            }
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

    @GetMapping("/catImage/{id}")
    @ResponseBody
    public byte[] getCatImage(@PathVariable int id) {
        Cat cat = catService.findById(id);
        return cat.getImage();
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

    @PostMapping("/updateCat")
    public String updateCat(@ModelAttribute("cat") Cat updatedCat,
                               HttpSession session,
                               Model model, @RequestParam ("imageFile") MultipartFile image) {
        try {
            Member member = (Member) session.getAttribute("member");
            if (member == null){
                return "redirect:/login";
            }

            if (!image.isEmpty()) {
                try {
                    byte[] bytes = image.getBytes();
                    updatedCat.setImage(bytes);
                } catch (IOException ioe){
                    model.addAttribute("error", "Kunne ikke oploade billede");
                    return "updateCat";
                }
            }

            updatedCat.setMemberId(member.getMemberId());

            catService.updateCat(updatedCat);

            model.addAttribute("cats", catService.findCatsByMemberId(member.getMemberId()));
            return "redirect:/catPage";
        } catch (ValidationExceptionCat ec){
            model.addAttribute("error", ec.getMessage());
            return "catPage";
        }
    }

    @GetMapping("/updateCat")
    public String showUpdateCatPage(@RequestParam("catId") int catId, HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");

        if (member == null){
            return "redirect:/login";
        }

        Cat cat = catService.findById(catId);

        if (cat == null || cat.getMemberId() != member.getMemberId()) {
            return "redirect:/catPage";
        }

        model.addAttribute("cat", cat);
        return "updateCat";
    }
    @PostMapping("/deleteCat")
    public String deleteCat(@RequestParam("catId") int catId,
                            HttpSession session) {

        Member member = (Member) session.getAttribute("member");

        if (member == null) {
            return "redirect:/catPage";
        }

        catService.deleteCat(catId);

        return "redirect:/catPage";
    }
}
