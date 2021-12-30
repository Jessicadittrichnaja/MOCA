package de.hsba.bi.project.web;

import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.events.EventService;
import de.hsba.bi.project.filter.Filter;
import de.hsba.bi.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/overview")
@ComponentScan("de.hsba.bi.project.events")
@RequiredArgsConstructor
public class FilterController {
    private final EventService eventService;
    private final EventRepository eventRepository;
    private final UserService userService;

    // zeigt alle verfügbaren Events (nicht vom angemeldeten User gebucht, mehr als 0 freie Plätze)

    @GetMapping
    public String eventList(Model model) {
        model.addAttribute("events", eventRepository.findAvailableEvents(userService.findCurrentUser()));
        model.addAttribute("filter", new Filter());
        return "overview";
    }
}
