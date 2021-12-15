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
    private EventFormConverter eventFormConverter;

    // Anlegen eines neuen Events

    @GetMapping("/eventPlanner/createEvent")
    public String Form(Model model) {
        model.addAttribute("event", new Event());
        return "eventPlanner/createEvent";
    }

    // Speichern des neuen Events

    @PostMapping("/eventPlanner/createEvent")
    public String saveEvent(@ModelAttribute("event") @Valid EventForm eventForm, BindingResult result) {
        if (result.hasErrors()) {
            return "eventPlanner/createEvent";
        }
        eventService.save(eventFormConverter.update(new Event(), eventForm));
        return "eventPlanner/result";
    }

    // Löschen eines Events

    @GetMapping("/eventPlanner/event/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id, Model model) {
        Event event = eventService.findById(id);
        eventService.removeEvent(event);
        model.addAttribute("events", eventService.findAll());
        return "redirect:/eventPlanner/event";
    }

    // Bearbeiten eines Events

    @GetMapping("/eventPlanner/event/edit/{id}")
    public String editEventPage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("eventForm", eventFormConverter.toForm(eventService.findEvent(id)));
        model.addAttribute("events", eventRepository.findAll());

        return "eventPlanner/editEvent";
    }

    // Schließen eines Events

    @GetMapping("/eventPlanner/event/close/{id}")
    public String closeEventPage(@PathVariable("id") Integer id, Model model) {
        Event event = eventService.findById(id);
        eventService.closeEvent(id);

        return "redirect:/eventPlanner/event";
    }

    // Speichert Änderungen, wenn valide

    @PostMapping("/eventPlanner/event/edit/{id}")
    public String editEvent(@PathVariable("id") Integer id, @ModelAttribute("eventForm") @Valid EventForm form, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "eventPlanner/editEvent";
        }

        Event event = eventService.findEvent(id);
        eventService.save(eventFormConverter.update(event, form));
        model.addAttribute("events", eventService.findAll());
        return "eventPlanner/event";
    }


}