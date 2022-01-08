package de.hsba.bi.project.filter;

import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import de.hsba.bi.project.events.Daytime;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsba.bi.project.events.EventService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilterService {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserService userService;

    // gibt nach Filterung verfügbare Events aus
    public List<Event> getFilteredEvents(Filter filter) {

        List<Event> filteredEvents = eventRepository.findAvailableEvents(userService.findCurrentUser());

        if (filter.getSelectedCategory() != null)
            filteredEvents.retainAll(eventService.findByCategory(filter.getSelectedCategory()));

        if (filter.getSelectedLocation() != null)
            filteredEvents.retainAll(eventService.findByLocation(filter.getSelectedLocation()));

        if (filter.getSelectedDate() != null)
            filteredEvents.retainAll(eventService.findByDate(filter.getSelectedDate()));

        if (filter.getSelectedHeadSeminar() != null)
            filteredEvents.retainAll(eventService.findByHeadSeminar(filter.getSelectedHeadSeminar()));

        if (filter.getSelectedDaytime() == Daytime.Morgens)
            filteredEvents.retainAll(eventRepository.findEventsMorning());
        if (filter.getSelectedDaytime() == Daytime.Vormittags)
            filteredEvents.retainAll(eventRepository.findEventsNoon());
        if (filter.getSelectedDaytime() == Daytime.Nachmittags)
            filteredEvents.retainAll(eventRepository.findEventsAfternoon());
        if (filter.getSelectedDaytime() == Daytime.Abends)
            filteredEvents.retainAll(eventRepository.findEventsEvening());

        return filteredEvents;
    }
}