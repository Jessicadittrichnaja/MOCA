package de.hsba.bi.project.events;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Component
public class EventService {

    @Autowired
    EventRepository eventRepository;

    // Methoden z.B. zum Löschen/ Speichern von Events

    public List<Event> findAll() {
        return (List<Event>) eventRepository.findAll();
    }
    public Event addEvent(Event event)  {
    event = eventRepository.save(event);
    return event;
    }

    public Event findById(Integer id) {

        Event event = eventRepository.findById(id).orElse(null);

        return event;

    };

    public List<Event> findByCategory(Category category) {
        List<Event> events = eventRepository.findByCategory(category);
        for (Event event : events) {
        }
        return events;
    };

    public List<Event> findByLocation(Location location) {
        List<Event> events = eventRepository.findByLocation(location);
        for (Event event : events) {
        }
        return events;
    };

    public List<Event> findByDate(LocalDate date) {
        List<Event> events = eventRepository.findByDate(date);
        for (Event event : events) {
        }
        return events;
    };

    public List<Event> findByTime(Time time) {
        List<Event> events = eventRepository.findByTime(time);
        for (Event event : events) {
        }
        return events;
    };
    public void removeEvent(Event event)  {
        eventRepository.delete(event);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event findEvent(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

}