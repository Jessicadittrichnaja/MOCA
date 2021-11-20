package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.events.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {

    @Autowired
    private EventRepository eventRepository;
    EventService eventService;

    @GetMapping("/createEvent")
    public String Form(Model model) {
        model.addAttribute("event", new Event());
        return "createEvent";
    }

     @PostMapping("/createEvent")
    public String saveEvent(@ModelAttribute Event event) {
        eventRepository.save(event);
        return "result";
    }

/*
   @GetMapping("/showEvents")
    public String showAllEvents(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "showEvents";
    }*/

}