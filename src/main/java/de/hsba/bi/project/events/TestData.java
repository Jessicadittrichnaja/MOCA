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
            Event programmierung = new Event ("Programmierung 2", "Ziel des Kurses Programmierung 2 ist das Erlernen grundlegender Konzepte der imperativen und objektorientierten Programmierung anhand der Programmiersprache Java.", Category.Seminar, 10, 1, Location.Hamburg, LocalDate.of(2021, 10, 23), LocalTime.of(10, 15));
            Event stadttour = new Event("Stadtrundfahrt", "Begeben Sie sich auf eine Stadtrundfahrt durch Berlin - mit Bus, Schiff, auf dem Fahrrad oder zu Fuß. Vorbei an den klassischen Sehenswürdigkeiten wie das Brandenburger Tor, das Reichstagsgebäude und der Checkpoint Charlie", Category.Teamtag, 3, 15, Location.Berlin, LocalDate.of(2021, 8, 20), LocalTime.of(11, 30));
            Event agileMethoden = new Event("Agile Methoden", "In diesem Seminar lernen Sie alles zum Thema agile Methoden.", Category.Seminar, 3, 15, Location.München, LocalDate.of(2021, 12, 8), LocalTime.of(15, 45));
            Event test1 = new Event ("Excel Kurs (Basiswissen)", "Der Umgang mit Microsoft Excel ist mittlerweile zu einer Grundvoraussetzung im Büroalltag geworden. Das Basiswissen und die wichtigsten Funktionalitäten von Microsoft Excel werden in diesem Kurs vermittelt.", Category.Seminar, 4, 15, Location.Hamburg, LocalDate.of(2021, 10, 23), LocalTime.of(9, 00));
            Event test2 = new Event("Excel Kurs (Advanced)", "Dieser Kurs deckt einen großen Teil des benötigten „Standard“ Excel Wissens für Angestellte ab. Der Kurs ist für Mitarbeiter, welche bereits viel Kontakt mit Excel haben und nun Ihr vorhandenes Wissen weiter vertiefen wollen.", Category.Teamtag, 8, 10, Location.Berlin, LocalDate.of(2021, 8, 20), LocalTime.of(10, 00));
            Event test3 = new Event("Buchführung und Bilanzierung", "Durch das Seminar „Buchführung und Bilanzierung“ werden Sie in der Lage sein, Buchführungsaufgaben nach HGB in Ihrem Unternehmen oder für Ihre Kunden sicher, vollständig und korrekt auszuüben.", Category.Seminar, 3, 15, Location.München, LocalDate.of(2021, 12, 8), LocalTime.of(18, 00));
            eventRepository.save(programmierung);
            eventRepository.save(stadttour);
            eventRepository.save(agileMethoden);
            eventRepository.save(test1);
            eventRepository.save(test2);
            eventRepository.save(test3);
        }
    }
