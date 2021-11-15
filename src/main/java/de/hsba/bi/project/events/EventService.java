/*package de.hsba.bi.project.events;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements IEventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void Seed(String name, String description, String category, int duration) {
        eventRepository.save(new Event(name, description, category, duration));
    }

    @Override
    public List<Event> findAll() {

        return eventRepository.findAll();
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }
}*/