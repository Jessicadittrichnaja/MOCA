package de.hsba.bi.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model, @RequestParam(name= "hello", required= false)String hello) {
        if(hello!= null) {
            model.addAttribute("greeting", "Hello, " + hello+ "!");
        }
        return"index";
    }

    @GetMapping(path= "/hello/{name}")
    public String hello(@PathVariable String name, Model model) {
        if(name!= null) {
            model.addAttribute("greeting", "Hello, " + name+ "!");
        }
        return "index";
    }
}
