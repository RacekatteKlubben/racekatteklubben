package com.example.racekatteklubben.controller;
import com.example.racekatteklubben.domain.Member;
import com.example.racekatteklubben.service.CatService;
import com.example.racekatteklubben.service.CatShowRegistrationService;
import com.example.racekatteklubben.service.CatShowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CatShowController {

    private final CatShowService catShowService;
    private final CatShowRegistrationService catShowRegistrationService;
    private final CatService catService;

    public CatShowController(CatShowService catShowService,
                             CatShowRegistrationService catShowRegistrationService,
                             CatService catService) {
        this.catShowService = catShowService;
        this.catShowRegistrationService = catShowRegistrationService;
        this.catService = catService;
    }

    @GetMapping("/catShows")
    public String showCatShows(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login";
        }

        model.addAttribute("member", member);
        model.addAttribute("catShows", catShowService.findAllCatShows());
        model.addAttribute("cats", catService.findCatsByMemberId(member.getMemberId()));

        return "catShows";
    }

    @PostMapping("/registerCatToShow")
    public String registerCatToShow(@RequestParam("catId") int catId,
                                    @RequestParam("catShowId") int catShowId,
                                    HttpSession session,
                                    Model model) {
        try {
            Member member = (Member) session.getAttribute("member");

            if (member == null) {
                return "redirect:/login";
            }

            catShowRegistrationService.registerCatToShow(catId, catShowId, member.getMemberId());

            return "redirect:/catShows";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());

            Member member = (Member) session.getAttribute("member");
            model.addAttribute("member", member);
            model.addAttribute("catShows", catShowService.findAllCatShows());
            model.addAttribute("cats", catService.findCatsByMemberId(member.getMemberId()));

            return "catShows";
        }
    }
    @GetMapping("/registered")
    public String showRegistered(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login";
        }

        model.addAttribute("member", member);
        model.addAttribute("cats", catService.findCatsByMemberId(member.getMemberId()));

        return "registered";
    }

    @PostMapping("/deleteRegistration")
    public String deleteRegistration(@RequestParam("registrationId") int registrationId,
                                     HttpSession session) {

        Member member = (Member) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login";
        }

        catShowRegistrationService.deleteRegistration(registrationId);

        return "redirect:/registered"; // vigtigt: tilbage til din side
    }
}