package de.hsba.bi.project.web;

import de.hsba.bi.project.form.form;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {

    @GetMapping("/overview")
    public String Form(Model model) {
        model.addAttribute("form", new form());
        return "overview";
    }

    @PostMapping("/overview")
    public String formSubmit(@ModelAttribute form form, Model model) {
        model.addAttribute("form", form);
        return "result";
    }

}