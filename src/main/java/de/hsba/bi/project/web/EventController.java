package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsba.bi.project.events.EventService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/event")
@ComponentScan("de.hsba.bi.project.events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public String eventList(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "event";
    }
}
