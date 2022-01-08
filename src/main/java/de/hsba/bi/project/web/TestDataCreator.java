package de.hsba.bi.project.web;

import de.hsba.bi.project.bookingProcess.Booking;
import de.hsba.bi.project.bookingProcess.BookingService;
import de.hsba.bi.project.events.*;
import de.hsba.bi.project.roles.Role;
import de.hsba.bi.project.roles.RoleRepository;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TestDataCreator {

    // Anlegen von Rollen und Test-Usern beim Start der Anwendung

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final EventService eventService;
    private final BookingService bookingService;

    @EventListener(ApplicationStartedEvent.class)
    @Transactional

    public void init() {

        // Erstellen der Rollen
        Role roleEmployee = new Role ("Mitarbeiter");
        Role roleHR = new Role ("Personalabteilung");
        Role roleEventPlanner = new Role ("Terminverwalter");
        roleRepository.save(roleEmployee);
        roleRepository.save(roleHR);
        roleRepository.save(roleEventPlanner);

        Set<Role> rolesEnrico = new HashSet<>();
        rolesEnrico.add(roleEventPlanner);

        Set<Role> rolesJessica = new HashSet<>();
        rolesJessica.add(roleHR);
        rolesJessica.add(roleEventPlanner);

        Set<Role> rolesFynn = new HashSet<>();
        rolesFynn.add(roleEventPlanner);

        Set<Role> rolesDaniel = new HashSet<>();
        rolesDaniel.add(roleHR);

       // Hinzufügen von Usern
        User enrico = createUser("Enrico", "Nehls", "enrico", "password", rolesEnrico);
        User jessica = createUser("Jessica", "Dittrich", "jessica", "password", rolesJessica);
        User fynn = createUser("Fynn", "Thode", "fynn", "password", rolesFynn);
        User daniel = createUser("Daniel", "Fuhrmann", "daniel", "password", rolesDaniel);

        // Erstellung Events
        Event programmierung = new Event ("Programmierung 2", "Ziel des Kurses Programmierung 2 ist das Erlernen grundlegender Konzepte der imperativen und objektorientierten Programmierung anhand der Programmiersprache Java.", headSeminar.Günther, Category.Seminar, 8, 1, Location.Seminarraum1, LocalDate.of(2022, 1, 23), LocalTime.of(10, 15), LocalTime.of(10,15).plusHours(8));
        Event backen = new Event("Weihnachtliches Kekse backen", "Werden Sie zum Keks-Profi in diesem lustigen Backkurs. Der stärkt nicht nur das Team, sondern auch Sie.", headSeminar.Anne, Category.Teamtag, 3, 15, Location.grosseHalle, LocalDate.of(2022, 12, 20), LocalTime.of(11, 30), LocalTime.of(11,30).plusHours(3));
        Event agileMethoden = new Event("Agile Methoden", "In diesem Seminar lernen Sie alles zum Thema agile Methoden.", headSeminar.Klausi, Category.Seminar, 3, 15, Location.Beamerraum, LocalDate.of(2022, 4, 8), LocalTime.of(15, 45), LocalTime.of(15,45).plusHours(3));
        Event excel1 = new Event ("Excel Kurs (Basiswissen)", "Der Umgang mit Microsoft Excel ist mittlerweile zu einer Grundvoraussetzung im Büroalltag geworden. Das Basiswissen und die wichtigsten Funktionalitäten von Microsoft Excel werden in diesem Kurs vermittelt.", headSeminar.Sabine, Category.Seminar, 4, 15, Location.Seminarraum2, LocalDate.of(2022, 10, 23), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(4));
        Event excel2 = new Event("Excel Kurs (Advanced)", "Dieser Kurs deckt einen großen Teil des benötigten „Standard“ Excel Wissens für Angestellte ab. Der Kurs ist für Mitarbeiter, welche bereits viel Kontakt mit Excel haben und nun Ihr vorhandenes Wissen weiter vertiefen wollen.", headSeminar.Günther, Category.Teamtag, 8, 10, Location.Seminarraum1, LocalDate.of(2022, 9, 21), LocalTime.of(10, 00), LocalTime.of(10,00).plusHours(8));
        Event buchführung = new Event("Buchführung und Bilanzierung", "Durch das Seminar „Buchführung und Bilanzierung“ werden Sie in der Lage sein, Buchführungsaufgaben nach HGB in Ihrem Unternehmen oder für Ihre Kunden sicher, vollständig und korrekt auszuüben.", headSeminar.Klausi, Category.Seminar, 3, 15, Location.Spiegelsaal, LocalDate.of(2022, 12, 8), LocalTime.of(18, 00), LocalTime.of(18,00).plusHours(3));
        Event tableau = new Event("Tableau (Basiswissen)", "Im Seminar „Tableau (Basiswissen)“ lernen Sie, Ihre Daten wie ein Profi zu visualisieren, um schneller Erkenntnisse aus Ihren Daten zu gewinnen und diese mit anderen zu teilen.", headSeminar.Günther, Category.Seminar, 8, 20, Location.grosseHalle, LocalDate.of(2022, 1, 8), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
        Event sql = new Event("SQL (Basiswissen)", "Im Seminar „SQL (Basiswissen)“ lernen Sie, Datenbankabfragen und Datenmanipulation durchzuführen. Lernen Sie, Daten geschickt miteinander zu verknüpfen und interessante Auswertungen zu erstellen.", headSeminar.Maxi, Category.Seminar, 8, 20, Location.Seminarraum1, LocalDate.of(2022, 1, 20), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
        Event programmierung1 = new Event ("Programmierung 2", "Ziel des Kurses Programmierung 2 ist das Erlernen grundlegender Konzepte der imperativen und objektorientierten Programmierung anhand der Programmiersprache Java.", headSeminar.Maxi, Category.Seminar, 8, 1, Location.Seminarraum1, LocalDate.of(2022, 3, 23), LocalTime.of(10, 15), LocalTime.of(10,15).plusHours(8));
        Event backen1 = new Event("Weihnachtliches Kekse backen", "Werden Sie zum Keks-Profi in diesem lustigen Backkurs. Der stärkt nicht nur das Team, sondern auch Sie.", headSeminar.Günther, Category.Teamtag, 3, 15, Location.grosseHalle, LocalDate.of(2023, 12, 20), LocalTime.of(11, 30), LocalTime.of(11,30).plusHours(3));
        Event agileMethoden1 = new Event("Agile Methoden", "In diesem Seminar lernen Sie alles zum Thema agile Methoden.", headSeminar.Sabine, Category.Seminar, 3, 15, Location.Beamerraum, LocalDate.of(2022, 5, 8), LocalTime.of(15, 45), LocalTime.of(15,45).plusHours(3));
        Event excel11 = new Event ("Excel Kurs (Basiswissen)", "Der Umgang mit Microsoft Excel ist mittlerweile zu einer Grundvoraussetzung im Büroalltag geworden. Das Basiswissen und die wichtigsten Funktionalitäten von Microsoft Excel werden in diesem Kurs vermittelt.", headSeminar.Anne, Category.Seminar, 4, 15, Location.Seminarraum2, LocalDate.of(2022, 12, 23), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(4));
        Event excel21 = new Event("Excel Kurs (Advanced)", "Dieser Kurs deckt einen großen Teil des benötigten „Standard“ Excel Wissens für Angestellte ab. Der Kurs ist für Mitarbeiter, welche bereits viel Kontakt mit Excel haben und nun Ihr vorhandenes Wissen weiter vertiefen wollen.", headSeminar.Maxi, Category.Teamtag, 8, 10, Location.Seminarraum1, LocalDate.of(2022, 10, 21), LocalTime.of(10, 00), LocalTime.of(10,00).plusHours(8));
        Event buchführung1 = new Event("Buchführung und Bilanzierung", "Durch das Seminar „Buchführung und Bilanzierung“ werden Sie in der Lage sein, Buchführungsaufgaben nach HGB in Ihrem Unternehmen oder für Ihre Kunden sicher, vollständig und korrekt auszuüben.", headSeminar.Günther, Category.Seminar, 3, 15, Location.Spiegelsaal, LocalDate.of(2022, 10, 8), LocalTime.of(18, 00), LocalTime.of(18,00).plusHours(3));
        Event tableau1 = new Event("Tableau (Basiswissen)", "Im Seminar „Tableau (Basiswissen)“ lernen Sie, Ihre Daten wie ein Profi zu visualisieren, um schneller Erkenntnisse aus Ihren Daten zu gewinnen und diese mit anderen zu teilen.", headSeminar.Klausi, Category.Seminar, 8, 20, Location.grosseHalle, LocalDate.of(2022, 3, 8), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
        Event sql1 = new Event("SQL (Basiswissen)", "Im Seminar „SQL (Basiswissen)“ lernen Sie, Datenbankabfragen und Datenmanipulation durchzuführen. Lernen Sie, Daten geschickt miteinander zu verknüpfen und interessante Auswertungen zu erstellen.", headSeminar.Sabine, Category.Seminar, 8, 20, Location.Seminarraum1, LocalDate.of(2022, 4, 20), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
        Event tableau2 = new Event("Tableau (Advanced)", "Im Seminar „Tableau (Advanced)“ steigen Sie in das Thema „Dashboards erstellen“ ein und lernen, all Ihre Visualisierungen auf einer Seite zu präsentieren.", headSeminar.Maxi, Category.Seminar, 8, 20, Location.grosseHalle, LocalDate.of(2022, 5, 10), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
        Event sql2 = new Event("SQL (Advanced)", "Im Seminar „SQL (Advanced)“ lernen Sie, Datenbanken in mySQL anzulegen und übersichtlich zu strukturieren. Sie lernen, Tabellen miteinander zu verknüpfen und daraus neue Tabellen zu schaffen.", headSeminar.Sabine, Category.Seminar, 8, 20, Location.Seminarraum1, LocalDate.of(2022, 6, 15), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(8));
        Event weinverkostung = new Event("Weinverkostung", "Stellen Sie ihre Weinkenntnisse unter Beweis in diesem lustigen Workshop. Probieren Sie Wein aus aller Welt und finden Sie Ihren neuen Favoriten.", headSeminar.Anne, Category.Workshop, 3, 15, Location.grosseHalle, LocalDate.of(2022, 8, 10), LocalTime.of(18, 00), LocalTime.of(18,00).plusHours(3));
        Event trommeln = new Event("Trommeln im Takt", "Verbessern Sie Ihr Rhythmusgefühl und trommeln Sie mit Ihren Kollegen um die Wette. Das Trommeln im Takt ist auch super fürs Teambuilding.", headSeminar.Maxi, Category.Workshop, 4, 30, Location.grosseHalle, LocalDate.of(2022, 6, 15), LocalTime.of(9, 00), LocalTime.of(9,00).plusHours(4));
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

        // Buchungen
        Booking booking1 = new Booking(excel1, enrico);
        Booking booking2 = new Booking(buchführung1, daniel);
        Booking booking3 = new Booking(tableau1, jessica);
        Booking booking4 = new Booking(sql1, fynn);
        Booking booking5 = new Booking(weinverkostung, enrico);
        Booking booking6 = new Booking(trommeln, daniel);
        Booking booking7 = new Booking(agileMethoden1, jessica);
        Booking booking8 = new Booking(sql2, fynn);
        Booking booking9 = new Booking(backen1, enrico);
        Booking booking10 = new Booking(backen1, daniel);
        Booking booking11 = new Booking(programmierung1, jessica);
        Booking booking12 = new Booking(tableau1, fynn);

        bookingService.save(booking1);
        eventService.removeSpot(eventService.findById(booking1.getEvent().getId()).getId());
        bookingService.save(booking2);
        eventService.removeSpot(eventService.findById(booking2.getEvent().getId()).getId());
        bookingService.save(booking3);
        eventService.removeSpot(eventService.findById(booking3.getEvent().getId()).getId());
        bookingService.save(booking4);
        eventService.removeSpot(eventService.findById(booking4.getEvent().getId()).getId());
        bookingService.save(booking5);
        eventService.removeSpot(eventService.findById(booking5.getEvent().getId()).getId());
        bookingService.save(booking6);
        eventService.removeSpot(eventService.findById(booking6.getEvent().getId()).getId());
        bookingService.save(booking7);
        eventService.removeSpot(eventService.findById(booking7.getEvent().getId()).getId());
        bookingService.save(booking8);
        eventService.removeSpot(eventService.findById(booking8.getEvent().getId()).getId());
        bookingService.save(booking9);
        eventService.removeSpot(eventService.findById(booking9.getEvent().getId()).getId());
        bookingService.save(booking10);
        eventService.removeSpot(eventService.findById(booking10.getEvent().getId()).getId());
        bookingService.save(booking11);
        eventService.removeSpot(eventService.findById(booking11.getEvent().getId()).getId());
        bookingService.save(booking12);
        eventService.removeSpot(eventService.findById(booking12.getEvent().getId()).getId());
    }

    private User createUser(String firstName, String lastName, String name, String password, Set<Role> roles) {
        return userService.save(new User(firstName, lastName, name, passwordEncoder.encode(password), roles));
    }
}
