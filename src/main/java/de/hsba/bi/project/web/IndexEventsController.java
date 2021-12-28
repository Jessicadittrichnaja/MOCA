package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.EventRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsba.bi.project.events.EventService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@ComponentScan("de.hsba.bi.project.events")
@RequiredArgsConstructor
public class IndexEventsController {

    private final EventRepository eventRepository;

    // zeigt Beispiel-Events auf der Index-Seite

    @GetMapping
    public String eventsIndex(Model model) {
        model.addAttribute("events", eventRepository.findTop3ByOrderByIdAsc());
        return "index";
    }

    @GetMapping("/index")
    public String eventsIndexToo(Model model) {
        model.addAttribute("events", eventRepository.findTop3ByOrderByIdAsc());
        return "index";
    }
}