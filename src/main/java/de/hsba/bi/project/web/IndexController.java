package de.hsba.bi.project.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
public class IndexController {


    @RequestMapping("/index")
    public String index(Model model) {

        return "index";
    }
    @RequestMapping("/overview")
    public String overview(Model model) {

        return "overview";
    }
    @RequestMapping("/login")
    public String login(Model model) {

        return "login";
    }
    @RequestMapping("/logout")
    public String logout(Model model) {

        return "logout";
    }
    @RequestMapping("/impressum")
    public String impressum(Model model) {

        return "impressum";
    }

    @RequestMapping("/kontakt")
    public String kontakt(Model model) {

        return "kontakt";
    }
    @RequestMapping("/datenschutzerklärung")
    public String datenschutzerklaerung(Model model) {

        return "datenschutzerklärung";
    }

    @RequestMapping("/createEvent")
    public String createEvent(Model model) {

        return "createEvent";
    }

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth instanceof AnonymousAuthenticationToken ? "login" : "redirect:/";
    }

}


