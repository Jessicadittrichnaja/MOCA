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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

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

    // Speichern des neuen Events. Das Event wird nur gespeichert, wenn es nicht bereits ein Event mit dem gleichen Namen in der Datenbank gibt und alle Felder gefüllt sind.

    @PostMapping("/eventPlanner/createEvent")
    public String saveEvent(@ModelAttribute("event") @Valid EventForm eventForm, BindingResult result, Model model) throws ParseException {
        if (result.hasErrors()) {
            return "eventPlanner/createEvent";
        }
        eventForm.setEndTime(eventForm.getStartTime().plusHours(eventForm.getDuration()));
        Event event = eventFormConverter.update(new Event(), eventForm);
        event.setEndTime(eventForm.getEndTime());

        /* nicht zweimal das gleiche Event
        if (eventService.countEventsWithSameData(event.getCategory(), event.getDate(), event.getDescription(), event.getDuration(), event.getEndTime(), event.getLocation(), event.getStartTime(), event.getName(), event.getSpots()) == 1) {
            model.addAttribute("error", "Das Event gibt es schon.");
            return ("eventPlanner/createEvent");
        }*/
        //im gleichen Raum können nicht gleichzeitig mehrere Events stattfinden
        if (eventService.countNumberEventsWithSameLocationAtSameTime(event.getLocation(), event.getDate(), event.getStartTime(), event.getEndTime()) == 1) {
            model.addAttribute("error", "Es gibt in dem Zeitraum schon ein Event im gewählten Raum.");
            return ("eventPlanner/createEvent");
        }
        // Ende des Events muss vor 24 Uhr sein (Beginn + Dauer)
        LocalTime startTime = event.getStartTime();
        LocalTime endingTime = startTime.plusHours(event.getDuration());
        if (endingTime.isAfter(LocalTime.parse("21:00"))) {
            model.addAttribute("error", "Das Event darf höchstens bis 21 Uhr gehen.");
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
        model.addAttribute("events", eventService.findAll());

        return "eventPlanner/editEvent";
    }

    // Speichert Änderungen, wenn valide

    @PostMapping("/eventPlanner/event/edit/{id}")
    public String editEvent(@PathVariable("id") Integer id, @ModelAttribute("eventForm") @Valid EventForm form, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "eventPlanner/editEvent";
        }

        form.setEndTime(form.getStartTime().plusHours(form.getDuration()));
        Event event = eventFormConverter.update(eventService.findEvent(id), form);
        event.setEndTime(form.getEndTime());
        // Ende des Events muss vor 24 Uhr sein (Beginn + Dauer)
        LocalTime startTime = event.getStartTime();
        LocalTime endingTime = startTime.plusHours(event.getDuration());
        if (endingTime.isAfter(LocalTime.parse("21:00"))) {
            model.addAttribute("error", "Das Event darf höchstens bis 21 Uhr gehen.");
            return ("eventPlanner/editEvent");
        }
        /* nicht zweimal das gleiche Event
        if (eventService.countEventsWithSameDataThatAreNotEvent(event.getCategory(), event.getDate(), event.getDescription(), event.getDuration(), event.getEndTime(), event.getLocation(), event.getStartTime(), event.getName(), id, event.getSpots()) == 1) {
            model.addAttribute("error", "Das Event gibt es schon.");
            return ("eventPlanner/editEvent");
        }*/
        //im gleichen Raum können nicht gleichzeitig mehrere Events stattfinden
        if (eventService.countNumberEventsWithSameLocationAtSameTimeExceptCurrentEvent(id, event.getLocation(), event.getDate(), event.getStartTime(), event.getEndTime()) == 1) {
            model.addAttribute("error", "Es gibt in dem Zeitraum schon ein Event im gewählten Raum.");
            return ("eventPlanner/editEvent");
        }
        eventService.save(event);
        model.addAttribute("events", eventService.findAll());
        model.addAttribute("endingTime", endingTime);
        return "eventPlanner/event";
    }

    // Schließen eines Events. Dann sind keine An- oder Abmeldungen mehr möglich.

    @GetMapping("/eventPlanner/event/close/{id}")
    public String closeEventPage(@PathVariable("id") Integer id, Model model) {
        Event event = eventService.findById(id);
        eventService.closeEvent(id);

        return "redirect:/eventPlanner/event";
    }

    // Öffnen eines Events. An- und Abmeldungen wieder möglich.

    @GetMapping("/eventPlanner/event/open/{id}")
    public String openEventPage(@PathVariable("id") Integer id, Model model) {
        Event event = eventService.findById(id);
        eventService.openEvent(id);

        return "redirect:/eventPlanner/event";
    }


}