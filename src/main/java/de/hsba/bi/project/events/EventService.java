package de.hsba.bi.project.events;

import de.hsba.bi.project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public Event addEvent(Event event) {
        event = eventRepository.save(event);
        return event;
    }

    public Event findById(Integer id) {
        Event event = eventRepository.findById(id).orElse(null);
        return event;
    }

    public List<Event> findByCategory(Category category) {
        List<Event> events = eventRepository.findByCategory(category);
        for (Event event : events) {
        }
        return events;
    }

    public List<Event> findByLocation(Location location) {
        List<Event> events = eventRepository.findByLocation(location);
        for (Event event : events) {
        }
        return events;
    }

    public List<Event> findByDate(LocalDate date) {
        List<Event> events = eventRepository.findByDate(date);
        for (Event event : events) {
        }
        return events;
    }

    public List<Event> findByHeadSeminar(headSeminar headSeminar) {
        List<Event> events = eventRepository.findByHeadSeminar(headSeminar);
        for (Event event : events) {
        }
        return events;
    }

    public List<Event> findEvents(User user) {
        return eventRepository.findAvailableEvents(user);
    }

    /*public Integer countEventsWithSameData(Category category, LocalDate date, String description, Integer duration, LocalTime endTime, Location location, LocalTime startTime, String name, Integer spots) {
        return eventRepository.countNumberEventsWithSameData(category, date, description, duration, endTime, location, startTime, name, spots);
    }

    public Integer countEventsWithSameDataThatAreNotEvent(Category category, LocalDate date, String description, Integer duration, LocalTime endTime, Location location, LocalTime startTime, String name, Integer id, Integer spots) {
        return eventRepository.countNumberEventsWithSameDataThatAreNotEvent(category, date, description, duration, endTime, location, startTime, name, id, spots);
    }*/

    public void removeEvent(Event event) {
        eventRepository.delete(event);
    }

    public void closeEvent(int id) {
        eventRepository.closeEvent(id);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event findEvent(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    public boolean isEventClosed(Integer id) {
        return eventRepository.isEventClosed(id);
    }

    public void openEvent(int id) {
        eventRepository.openEvent(id);
    }

    public Boolean isEventOpen(Integer id) {
        return eventRepository.isEventOpen(id);
    }

    public void removeSpot(Integer id) {
        eventRepository.removeSpot(id);
    }

    public void addSpot(Integer id) {
        eventRepository.addSpot(id);
    }

    public void addSpotWhenUserDeleted(User user) {
        eventRepository.addSpotWhenUserDeleted(user);
    }

    public List<Event> findTop3() {
        return eventRepository.findTop3ByOrderByIdAsc();
    }

    public Integer countNumberEventsWithSameLocationAtSameTime(Location location, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return eventRepository.countNumberEventsWithSameLocationAtSameTime(location, date, startTime, endTime);
    }

    public Integer countNumberEventsWithSameLocationAtSameTimeExceptCurrentEvent(Integer id, Location location, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return eventRepository.countNumberEventsWithSameLocationAtSameTimeExceptCurrentEvent(id, location, date, startTime, endTime);
    }

    public Integer countNumberEventsWithSameHeadSeminarAtSameTime(headSeminar headSeminar, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return eventRepository.countNumberEventsWithSameHeadSeminarAtSameTime(headSeminar, date, startTime, endTime);
    }

    public Integer countNumberEventsWithSameHeadSeminarAtSameTimeExceptCurrentEvent(Integer id, headSeminar headSeminar, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return eventRepository.countNumberEventsWithSameHeadSeminarAtSameTimeExceptCurrentEvent(id, headSeminar, date, startTime, endTime);
    }
}