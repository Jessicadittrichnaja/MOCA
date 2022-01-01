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
            Event programmierung = new Event ("Programmierung 2", "Ziel des Kurses Programmierung 2 ist das Erlernen grundlegender Konzepte der imperativen und objektorientierten Programmierung anhand der Programmiersprache Java.", Category.Seminar, 8, 1, Location.Seminarraum1, LocalDate.of(2022, 1, 23), LocalTime.of(10, 15), LocalTime.of(10,15).plusHours(8));
            Event backen = new Event("Weihnachtliches Kekse backen", "Werden Sie zum Keks-Profi in diesem lustigen Backkurs. Der stärkt nicht nur das Team, sondern auch Sie.", Category.Teamtag, 3, 15, Location.grosseHalle, LocalDate.of(2022, 12, 20), LocalTime.of(11, 30), LocalTime.of(11,30).plusHours(3));
            Event agileMethoden = new Event("Agile Methoden", "In diesem Seminar lernen Sie alles zum Thema agile Methoden.", Category.Seminar, 3, 15, Location.Beamerraum, LocalDate.of(2022, 4, 8), LocalTime.of(15, 45), LocalTime.of(15,45).plusHours(3));
            Event excel1 = new Event ("Excel Kurs (Basiswissen)", "Der Umgang mit Microsoft Excel ist mittlerweile zu einer Grundvoraussetzung im Büroalltag geworden. Das Basiswissen und die wichtigsten Funktionalitäten von Microsoft Excel werden in diesem Kurs vermittelt.", Category.Seminar, 4, 15, Location.Seminarraum2, LocalDate.of(2022, 10, 23), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(4));
            Event excel2 = new Event("Excel Kurs (Advanced)", "Dieser Kurs deckt einen großen Teil des benötigten „Standard“ Excel Wissens für Angestellte ab. Der Kurs ist für Mitarbeiter, welche bereits viel Kontakt mit Excel haben und nun Ihr vorhandenes Wissen weiter vertiefen wollen.", Category.Teamtag, 8, 10, Location.Seminarraum1, LocalDate.of(2022, 9, 21), LocalTime.of(10, 00), LocalTime.of(10,00).plusHours(8));
            Event buchführung = new Event("Buchführung und Bilanzierung", "Durch das Seminar „Buchführung und Bilanzierung“ werden Sie in der Lage sein, Buchführungsaufgaben nach HGB in Ihrem Unternehmen oder für Ihre Kunden sicher, vollständig und korrekt auszuüben.", Category.Seminar, 3, 15, Location.Spiegelsaal, LocalDate.of(2022, 12, 8), LocalTime.of(18, 00), LocalTime.of(18,00).plusHours(3));
            Event tableau = new Event("Tableau (Basiswissen)", "Im Seminar „Tableau (Basiswissen)“ lernen Sie, Ihre Daten wie ein Profi zu visualisieren, um schneller Erkenntnisse aus Ihren Daten zu gewinnen und diese mit anderen zu teilen.", Category.Seminar, 8, 20, Location.grosseHalle, LocalDate.of(2022, 1, 8), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
            Event sql = new Event("SQL (Basiswissen)", "Im Seminar „SQL (Basiswissen)“ lernen Sie, Datenbankabfragen und Datenmanipulation durchzuführen. Lernen Sie, Daten geschickt miteinander zu verknüpfen und interessante Auswertungen zu erstellen.", Category.Seminar, 8, 20, Location.Seminarraum1, LocalDate.of(2022, 1, 20), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
            Event programmierung1 = new Event ("Programmierung 2", "Ziel des Kurses Programmierung 2 ist das Erlernen grundlegender Konzepte der imperativen und objektorientierten Programmierung anhand der Programmiersprache Java.", Category.Seminar, 8, 1, Location.Seminarraum1, LocalDate.of(2022, 3, 23), LocalTime.of(10, 15), LocalTime.of(10,15).plusHours(8));
            Event backen1 = new Event("Weihnachtliches Kekse backen", "Werden Sie zum Keks-Profi in diesem lustigen Backkurs. Der stärkt nicht nur das Team, sondern auch Sie.", Category.Teamtag, 3, 15, Location.grosseHalle, LocalDate.of(2023, 12, 20), LocalTime.of(11, 30), LocalTime.of(11,30).plusHours(3));
            Event agileMethoden1 = new Event("Agile Methoden", "In diesem Seminar lernen Sie alles zum Thema agile Methoden.", Category.Seminar, 3, 15, Location.Beamerraum, LocalDate.of(2022, 5, 8), LocalTime.of(15, 45), LocalTime.of(15,45).plusHours(3));
            Event excel11 = new Event ("Excel Kurs (Basiswissen)", "Der Umgang mit Microsoft Excel ist mittlerweile zu einer Grundvoraussetzung im Büroalltag geworden. Das Basiswissen und die wichtigsten Funktionalitäten von Microsoft Excel werden in diesem Kurs vermittelt.", Category.Seminar, 4, 15, Location.Seminarraum2, LocalDate.of(2022, 12, 23), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(4));
            Event excel21 = new Event("Excel Kurs (Advanced)", "Dieser Kurs deckt einen großen Teil des benötigten „Standard“ Excel Wissens für Angestellte ab. Der Kurs ist für Mitarbeiter, welche bereits viel Kontakt mit Excel haben und nun Ihr vorhandenes Wissen weiter vertiefen wollen.", Category.Teamtag, 8, 10, Location.Seminarraum1, LocalDate.of(2022, 10, 21), LocalTime.of(10, 00), LocalTime.of(10,00).plusHours(8));
            Event buchführung1 = new Event("Buchführung und Bilanzierung", "Durch das Seminar „Buchführung und Bilanzierung“ werden Sie in der Lage sein, Buchführungsaufgaben nach HGB in Ihrem Unternehmen oder für Ihre Kunden sicher, vollständig und korrekt auszuüben.", Category.Seminar, 3, 15, Location.Spiegelsaal, LocalDate.of(2022, 10, 8), LocalTime.of(18, 00), LocalTime.of(18,00).plusHours(3));
            Event tableau1 = new Event("Tableau (Basiswissen)", "Im Seminar „Tableau (Basiswissen)“ lernen Sie, Ihre Daten wie ein Profi zu visualisieren, um schneller Erkenntnisse aus Ihren Daten zu gewinnen und diese mit anderen zu teilen.", Category.Seminar, 8, 20, Location.grosseHalle, LocalDate.of(2022, 3, 8), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
            Event sql1 = new Event("SQL (Basiswissen)", "Im Seminar „SQL (Basiswissen)“ lernen Sie, Datenbankabfragen und Datenmanipulation durchzuführen. Lernen Sie, Daten geschickt miteinander zu verknüpfen und interessante Auswertungen zu erstellen.", Category.Seminar, 8, 20, Location.Seminarraum1, LocalDate.of(2022, 4, 20), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
            Event tableau2 = new Event("Tableau (Advanced)", "Im Seminar „Tableau (Advanced)“ steigen Sie in das Thema „Dashboards erstellen“ ein und lernen, all Ihre Visualisierungen auf einer Seite zu präsentieren.", Category.Seminar, 8, 20, Location.grosseHalle, LocalDate.of(2022, 5, 10), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
            Event sql2 = new Event("SQL (Advanced)", "Im Seminar „SQL (Advanced)“ lernen Sie, Datenbanken in mySQL anzulegen und übersichtlich zu strukturieren. Sie lernen, Tabellen miteinander zu verknüpfen und daraus neue Tabellen zu schaffen.", Category.Seminar, 8, 20, Location.Seminarraum1, LocalDate.of(2022, 6, 15), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
            Event weinverkostung = new Event("Weinverkostung", "Stellen Sie ihre Weinkenntnisse unter Beweis in diesem lustigen Workshop. Probieren Sie Wein aus aller Welt und finden Sie Ihren neuen Favoriten.", Category.Workshop, 3, 15, Location.grosseHalle, LocalDate.of(2022, 8, 10), LocalTime.of(18, 00), LocalTime.of(18,00).plusHours(3));
            Event trommeln = new Event("Trommeln im Takt", "Verbessern Sie Ihr Rhythmusgefühl und trommeln Sie mit Ihren Kollegen um die Wette. Das Trommeln im Takt ist auch super fürs Teambuilding.", Category.Workshop, 4, 30, Location.grosseHalle, LocalDate.of(2022, 6, 15), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(4));
            eventService.save(programmierung);
            eventService.save(backen);
            eventService.save(agileMethoden);
            eventService.save(excel1);
            eventService.save(excel2);
            eventService.save(buchführung);
            eventService.save(tableau);
            eventService.save(sql);
            eventService.save(programmierung1);
            eventService.save(backen1);
            eventService.save(agileMethoden1);
            eventService.save(excel11);
            eventService.save(excel21);
            eventService.save(buchführung1);
            eventService.save(tableau1);
            eventService.save(sql1);
            eventService.save(tableau2);
            eventService.save(sql2);
            eventService.save(weinverkostung);
            eventService.save(trommeln);
        }
    }
