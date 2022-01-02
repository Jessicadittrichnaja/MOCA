package de.hsba.bi.project.EventCreationProcess;


import de.hsba.bi.project.events.Category;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.events.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    String              name;
    String              description;
    Category            category;
    Integer             duration;
    Integer             spots;
    Location            location;
    LocalDate           date;
    LocalTime           startTime;
    LocalTime           endTime;

    @Autowired
    EventRepository     underTest;

    @Test
    @DisplayName("Creating a new Event")

    void testCreateEvent() {

        // given
        name        = new String("Programmierung 2");
        description = new String("Programmierveranstaltung");
        category    = Category.Seminar;
        duration    = 3;
        spots       = 1;
        location    = Location.Seminarraum1;
        date        = LocalDate.of(2021, 10, 23);
        startTime   = LocalTime.of(15, 30);
        endTime     = LocalTime.of(15,30).plusHours(3);

        // when
        Event event = new Event(name, description, category, duration, spots, location, date,startTime,endTime);

        // then
        String name         = "Programmierung 2";
        String description  = "Programmierveranstaltung";
        String category     = "Seminar";
        Integer duration    =  3;
        Integer spots       =  1;
        String location     = "Seminarraum1";
        LocalDate date      = LocalDate.of(2021,10,23);
        LocalTime startTime = LocalTime.of(15,30);
        LocalTime endTime   = LocalTime.of(15,30).plusHours(duration);

        assertThat(event.getCategory().toString()).isEqualTo(category);
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
        assertThat(event.getDuration()).isEqualTo(duration);
        assertThat(event.getSpots()).isEqualTo(spots);
        assertThat(event.getLocation().toString()).isEqualTo(location);
        assertThat(event.getDate()).isEqualTo(date);
        assertThat(event.getStartTime()).isEqualTo(startTime);
        assertThat(event.getEndTime()).isEqualTo(endTime);
    }

}