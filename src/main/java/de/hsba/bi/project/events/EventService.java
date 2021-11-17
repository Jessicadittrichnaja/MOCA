package de.hsba.bi.project.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<Event> getEvents() {
    List<Event> eventList = new ArrayList<>();
    eventRepository.findAll().forEach(eventList::add);
    return eventList;
    }

    public Event addEvent(Event event)  {
    event = eventRepository.save(event);
    return event;
    }

}