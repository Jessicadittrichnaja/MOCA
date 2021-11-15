package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/*import de.hsba.bi.project.events.EventService;*/

@RequiredArgsConstructor
@Controller
public class EventController {

/*    @Autowired
    private EventRepository eventRepository;

    private final EventService eventService;
/*    private final EventCreator eventCreator;*/

    @GetMapping("/createEvent")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "createEvent";
    }

    @PostMapping("/saveEvent")
    public String saveEventSubmission(@ModelAttribute Event event) {
        // TODO: save project in DB here
        return "result";
    }

    @GetMapping("/eventOverview")
    public String showAllProducts(Model model) {
/*        model.addAttribute("events", eventRepository.findAllEvents());*/
        return "eventOverview";
    }
}
