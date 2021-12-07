package de.hsba.bi.project.web;

import de.hsba.bi.project.events.EventService;
import de.hsba.bi.project.filter.Filter;
import de.hsba.bi.project.filter.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class FilteredEventsController {

    @Autowired
    private EventService eventService;
    @Autowired
    private FilterService filterService;
    @Autowired
    private Filter filter;


    @PostMapping(path = "/filterEvents")
    public String showFilterResults(@ModelAttribute("filter") Filter filter, BindingResult bindingResult,
                                    Model model) {
        model.addAttribute("events", filterService.getFilteredEvents(filter));
        return "overview";
    }
}
