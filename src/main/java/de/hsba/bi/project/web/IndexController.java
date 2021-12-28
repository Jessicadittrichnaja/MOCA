package de.hsba.bi.project.web;

import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private EventRepository eventRepository;

    // Mapping von diversen Seiten, die nicht in anderen Controllern aufgeführt werden

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

    @RequestMapping("/editPassword")
    public String editPassword(Model model) {

        return "editPassword";
    }

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth instanceof AnonymousAuthenticationToken ? "login" : "redirect:/";
    }

}


