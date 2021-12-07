package de.hsba.bi.project.events;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestData {

        private final EventService eventService;
        private final EventRepository eventRepository;

    @EventListener(ApplicationStartedEvent.class)
        @Transactional
        public void init() {
            // add some events
            Event programmierung = new Event ("Programmierung 2", "Projekt", Category.Seminar, 10, 20, Location.Hamburg, LocalDate.of(2021, 8, 23), Time.Morgens);
            Event stadttour = new Event("Stadttour", "Freizeitveranstaltung", Category.Teamtag, 3, 15, Location.Berlin, LocalDate.of(2021, 8, 23), Time.Vormittags);
            eventRepository.save(programmierung);
            eventRepository.save(stadttour);
        }
    }
