package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class EventFormConverter {
    @Autowired
    private PasswordEncoder Encoder;

    // siehe EventForm-Kommentar

    EventForm toForm(Event event) {
        EventForm form = new EventForm();
        form.setName(event.getName());
        form.setDescription(event.getDescription());
        form.setHeadSeminar(event.getHeadSeminar());
        form.setCategory(event.getCategory());
        form.setDuration(event.getDuration());
        form.setLocation(event.getLocation());
        form.setDate(event.getDate());
        form.setSpots(event.getSpots());
        form.setStartTime(event.getStartTime());
        return form;
    }

    Event update(Event event, EventForm form) {
        event.setName(form.getName());
        event.setDescription(form.getDescription());
        event.setHeadSeminar(form.getHeadSeminar());
        event.setCategory(form.getCategory());
        event.setDuration(form.getDuration());
        event.setLocation(form.getLocation());
        event.setDate(form.getDate());
        event.setSpots(form.getSpots());
        event.setStartTime(form.getStartTime());
        return event;
    }

}

