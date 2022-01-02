package de.hsba.bi.project.bookingProcess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hsba.bi.project.events.Category;
import de.hsba.bi.project.events.Location;
import de.hsba.bi.project.roles.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class BookingTest {

    Event event;
    User user;
    UserService userService;
    BookingRepository bookingRepository;
    BookingService bookingService;


    @Autowired
    BookingRepository underTest;

    @Test
    @DisplayName("Creating new booking should work")
    void testCreateBooking() {
        // given
        Role roleEmployee = new Role ("MITARBEITER");
        Set<Role> rolesEnrico = new HashSet<>();
        rolesEnrico.add(roleEmployee);
        event = new Event ("Programmierung 2", "Projekt", Category.Seminar, 8, 1, Location.Seminarraum1, LocalDate.of(2021, 10, 23), LocalTime.of(15, 30), LocalTime.of(15, 30).plusHours(8));
        user  = new User("Enrico", "password", rolesEnrico);
        // when
        Booking booking = new Booking(event, user);
        // then
        assertThat(booking.getEvent().getCategory().toString()).isEqualTo("Seminar");
        assertThat(booking.getUser().getName()).isEqualTo("Enrico");
        System.out.printf("%s hat eine Veranstaltung mit der Kategorie %s gebucht.", booking.getUser().getName(), booking.getEvent().getCategory());

    }

}