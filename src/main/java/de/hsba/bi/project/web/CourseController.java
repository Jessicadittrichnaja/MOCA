package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.events.EventService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
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

    // Speichern des neuen Events. Das Event wird nur gespeichert, wenn es nicht bereits das gleiche Event in der Datenbank gibt (es werden alle Übereinstimmungen außer is_closed geprüft)

    @PostMapping("/eventPlanner/createEvent")
    public String saveEvent(@ModelAttribute("event") @Valid EventForm eventForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "eventPlanner/createEvent";
        }
        Event event = eventFormConverter.update(new Event(), eventForm);
        if (eventRepository.countNumberEventsWithSameData(event.getName(), event.getCategory(), event.getDate(), event.getDescription(), event.getDuration(), event.getLocation(), event.getTime(), event.getSpots()) == 1) {
            model.addAttribute("error", "Das Event gibt es schon.");
            return ("eventPlanner/createEvent");
        }
        eventService.save(event);
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

    // Speichert Änderungen, wenn valide

    @PostMapping("/eventPlanner/event/edit/{id}")
    public String editEvent(@PathVariable("id") Integer id, @ModelAttribute("eventForm") @Valid EventForm form, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "eventPlanner/editEvent";
        }

    // Speichert Änderungen nur, wenn es auch welche gibt.

        Event event = eventFormConverter.update(eventService.findEvent(id), form);
        if (eventRepository.countNumberEventsWithSameData(event.getName(), event.getCategory(), event.getDate(), event.getDescription(), event.getDuration(), event.getLocation(), event.getTime(), event.getSpots()) == 1) {
            model.addAttribute("error", "Es wurde noch nichts verändert. Es gibt nichts zu speichern.");
            return "eventPlanner/editEvent";
        }
        eventService.save(event);
        model.addAttribute("events", eventService.findAll());
        return "eventPlanner/event";
    }

    // Schließen eines Events

    @GetMapping("/eventPlanner/event/close/{id}")
    public String closeEventPage(@PathVariable("id") Integer id, Model model) {
        Event event = eventService.findById(id);
        eventService.closeEvent(id);

        return "redirect:/eventPlanner/event";
    }

    // Öffnen eines Events

    @GetMapping("/eventPlanner/event/open/{id}")
    public String openEventPage(@PathVariable("id") Integer id, Model model) {
        Event event = eventService.findById(id);
        eventService.openEvent(id);

        return "redirect:/eventPlanner/event";
    }


}