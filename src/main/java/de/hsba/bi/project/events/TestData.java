package de.hsba.bi.project.events;

import de.hsba.bi.project.roles.Role;
import de.hsba.bi.project.roles.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class TestData {

        private final EventService eventService;
        private final EventRepository eventRepository;

    @EventListener(ApplicationStartedEvent.class)
        @Transactional
        public void init() {
            // add some events
            Event programmierung = new Event ("Programmierung 2", "Projekt", Category.Seminar, 10, 1, Location.Hamburg, LocalDate.of(2021, 10, 23), LocalTime.of(10, 15));
            Event stadttour = new Event("Stadttour", "Freizeitveranstaltung", Category.Teamtag, 3, 15, Location.Berlin, LocalDate.of(2021, 8, 20), LocalTime.of(11, 30));
            Event agileMethoden = new Event("Agile Methoden", "In diesem Seminar lernen Sie alles zum Thema agile Methoden", Category.Seminar, 3, 15, Location.München, LocalDate.of(2021, 12, 8), LocalTime.of(15, 45));
            Event test1 = new Event ("Test", "Test 1", Category.Seminar, 10, 1, Location.Hamburg, LocalDate.of(2021, 10, 23), LocalTime.of(9, 00));
            Event test2 = new Event("Test Test", "Test 2", Category.Teamtag, 3, 15, Location.Berlin, LocalDate.of(2021, 8, 20), LocalTime.of(10, 00));
            Event test3 = new Event("Test Test Test", "Test 3", Category.Seminar, 3, 15, Location.München, LocalDate.of(2021, 12, 8), LocalTime.of(18, 00));
            eventRepository.save(programmierung);
            eventRepository.save(stadttour);
            eventRepository.save(agileMethoden);
            eventRepository.save(test1);
            eventRepository.save(test2);
            eventRepository.save(test3);
        }
    }
