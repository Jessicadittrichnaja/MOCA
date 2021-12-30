package de.hsba.bi.project.filter;

import java.util.List;

import javax.transaction.Transactional;

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

        //if (filter.getSelectedTime() != null)
        //    filteredResults.retainAll(eventService.findByTime(filter.getSelectedTime()));

        return filteredEvents;
    }
}