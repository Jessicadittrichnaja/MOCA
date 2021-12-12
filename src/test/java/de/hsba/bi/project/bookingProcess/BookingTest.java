package de.hsba.bi.project.bookingProcess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hsba.bi.project.events.Category;
import de.hsba.bi.project.events.Location;
import de.hsba.bi.project.events.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserService;
import de.hsba.bi.project.bookingProcess.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.LocalDate;

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
        event = new Event ("Programmierung 2", "Projekt", Category.Seminar, 10, 1, Location.Hamburg, LocalDate.of(2021, 10, 23), Time.Morgens);
        user  = new User("Enrico", "password", User.MITARBEITER_ROLE);
        // when
        Booking booking = new Booking(event, user);
        // then
        String category = "Seminar";
        String name = "Enrico";
        assertThat(booking.getEvent().getCategory().toString()).isEqualTo(category);
        assertThat(booking.getUser().getName()).isEqualTo(name);
        System.out.printf("%s hat eine Veranstaltung mit der Kategorie %s gebucht.", booking.getUser().getName(), booking.getEvent().getCategory());

    }

}