package de.hsba.bi.project.filter;

import java.util.List;

import javax.transaction.Transactional;

import de.hsba.bi.project.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsba.bi.project.events.EventService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilterService {
    @Autowired
    private EventService eventService;

    public List<Event> getFilteredEvents(Filter filter) {

        List<Event> filteredResults = eventService.findAll();

        if (filter.getSelectedCategory() != null)
            filteredResults.retainAll(eventService.findByCategory(filter.getSelectedCategory()));

        if (filter.getSelectedLocation() != null)
            filteredResults.retainAll(eventService.findByLocation(filter.getSelectedLocation()));

        if (filter.getSelectedDate() != null)
            filteredResults.retainAll(eventService.findByDate(filter.getSelectedDate()));

        if (filter.getSelectedTime() != null)
            filteredResults.retainAll(eventService.findByTime(filter.getSelectedTime()));

        return filteredResults;
    }
}