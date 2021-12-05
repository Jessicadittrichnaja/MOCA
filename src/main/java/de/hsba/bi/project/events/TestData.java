package de.hsba.bi.project.events;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class TestData {

        private final EventService eventService;
        private final EventRepository eventRepository;

        @EventListener(ApplicationStartedEvent.class)
        @Transactional
        public void init() {
            // add some events
            Event programmierung = new Event ("Programmierung 2", "Projekt", Category.Seminar, 10, 20);
            Event stadttour = new Event("Stadttour", "Freizeitveranstaltung", Category.Teamtag, 3, 15);
            eventRepository.save(programmierung);
            eventRepository.save(stadttour);
        }
    }
