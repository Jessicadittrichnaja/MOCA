package de.hsba.bi.project.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<Event> findAll() {
        return (List<Event>) eventRepository.findAll();
    }
    public Event addEvent(Event event)  {
    event = eventRepository.save(event);
    return event;
    }

}