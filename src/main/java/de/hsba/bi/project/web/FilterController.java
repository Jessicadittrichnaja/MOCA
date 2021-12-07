package de.hsba.bi.project.web;

import de.hsba.bi.project.events.EventService;
import de.hsba.bi.project.filter.Filter;
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

    @GetMapping
    public String eventList(Model model) {
        model.addAttribute("events", eventService.findAll());
        model.addAttribute("filter", new Filter());
        return "overview";
    }
}
