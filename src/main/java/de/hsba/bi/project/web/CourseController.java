package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.events.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class CourseController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private FormAssembler formAssembler;

    @GetMapping("/createEvent")
    public String Form(Model model) {
        model.addAttribute("event", new Event());
        return "createEvent";
    }


    @PostMapping("/createEvent")
    public String saveEvent(@ModelAttribute("event") @Valid EventForm eventForm, BindingResult result) {
        if (result.hasErrors()) {
            return "createEvent";
        }
        eventService.save(formAssembler.update(new Event(), eventForm));
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
        model.addAttribute("eventForm", formAssembler.toForm(eventService.findEvent(id)));
        model.addAttribute("events", eventRepository.findAll());

        return "editEvent";
    }

    @PostMapping("/event/edit/{id}")
    public String editEvent(@PathVariable("id") Integer id, @ModelAttribute("eventForm") @Valid EventForm form, BindingResult binding) {
        if (binding.hasErrors()) {
            return "editEvent";
        }

        Event event = eventService.findEvent(id);
        eventService.save(formAssembler.update(event, form));
        return "redirect:/event";
    }

}