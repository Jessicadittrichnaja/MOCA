package de.hsba.bi.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}


