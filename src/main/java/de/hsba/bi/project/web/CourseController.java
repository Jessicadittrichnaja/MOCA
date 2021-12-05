package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.events.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CourseController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;

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

    @GetMapping("/event/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id, Model model) {
        Event event = eventService.findById(id);
        eventService.removeEvent(event);
        model.addAttribute("events", eventService.findAll());
        return "event";
    }

    @GetMapping("/event/edit/{id}")
    public String editEventPage(@PathVariable("id") Integer id, Model model) {
        Event event = eventService.findById(id);
        model.addAttribute("event", event);
        model.addAttribute("events", eventRepository.findAll());

        return "editEvent";
    }

    @PostMapping("/event/edit/{id}")
    public String editEvent(@PathVariable("id") Integer id, @Valid Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            event.setId(id);
            return "redirect:/index";
        }

        eventService.save(event);

        return "redirect:/event";
    }

/*
   @GetMapping("/showEvents")
    public String showAllEvents(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "showEvents";
    }*/

}