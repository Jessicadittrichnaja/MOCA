package de.hsba.bi.project.events;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    @GetMapping("/createEvent")
    public String createProjectForm(Model model) {
        model.addAttribute("event", new Event());
        return "createEvent";
    }

    @PostMapping("/saveEvent")
    public String saveProjectSubmission(@ModelAttribute Event event) {

/*        model.addAttribute("event", EventRepository.findAll());*/

        return "result";
    }
}
