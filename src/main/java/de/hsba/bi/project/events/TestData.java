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
            Event programmierung = new Event ("Programmierung 2", "Projekt", Category.Seminar, 10, 20, Location.Hamburg, LocalDate.of(2021, 10, 23), Time.Morgens);
            Event stadttour = new Event("Stadttour", "Freizeitveranstaltung", Category.Teamtag, 3, 15, Location.Berlin, LocalDate.of(2021, 8, 20), Time.Vormittags);
            Event agileMethoden = new Event("Agile Methoden", "In diesem Seminar lernen Sie alles zum Thema agile Methoden", Category.Seminar, 3, 15, Location.München, LocalDate.of(2021, 12, 8), Time.Morgens);
            eventRepository.save(programmierung);
            eventRepository.save(stadttour);
            eventRepository.save(agileMethoden);
        }
    }
