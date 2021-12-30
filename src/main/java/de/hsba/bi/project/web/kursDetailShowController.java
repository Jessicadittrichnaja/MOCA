package de.hsba.bi.project.web;

import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.events.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class kursDetailShowController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;

    // zeigt Details zu einem Event

    @GetMapping("/kursDetails/{id}")
    public String showChosenEvent(@PathVariable("id") int id, Model model) {
        model.addAttribute("events", eventService.findById(id));
        return "kursDetails";
    }
}
