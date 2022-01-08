package de.hsba.bi.project.filterProcess;

import de.hsba.bi.project.events.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterTest {

    String name;
    String description;
    de.hsba.bi.project.events.headSeminar headSeminar;
    Category selectedCategory;
    Integer duration;
    Integer spots;
    Location selectedLocation;
    LocalDate selectedDate;
    LocalTime startTime;
    LocalTime endTime;

    @Autowired
    EventRepository underTest;

    @Test
    @DisplayName("Creating a new Event")
    void testCreateEvent() {

        // given
        name = new String("Weinverkostung");
        description = new String("Stellen Sie ihre Weinkenntnisse unter Beweis in diesem lustigen Workshop. Probieren Sie Wein aus aller Welt und finden Sie Ihren neuen Favoriten.");
        headSeminar = headSeminar.Günther;
        selectedCategory = Category.Seminar;
        duration = 2;
        spots = 10;
        selectedLocation = Location.Seminarraum1;
        selectedDate = LocalDate.of(2022, 11, 21);
        startTime = LocalTime.of(15, 30);
        endTime = LocalTime.of(15, 30).plusHours(2);

        // when
        Event event = new Event(name, description, headSeminar, selectedCategory, duration, spots, selectedLocation, selectedDate, startTime, endTime);

        // then
        String name = "Weinverkostung";
        String description = "Stellen Sie ihre Weinkenntnisse unter Beweis in diesem lustigen Workshop. Probieren Sie Wein aus aller Welt und finden Sie Ihren neuen Favoriten.";
        String headSeminar = "Günther";
        String selectedCategory = "Seminar";
        Integer duration = 2;
        Integer spots = 10;
        String selectedLocation = "Seminarraum1";
        LocalDate selectedDate = LocalDate.of(2022, 11, 21);
        LocalTime startTime = LocalTime.of(15, 30);
        LocalTime endTime = LocalTime.of(15, 30).plusHours(duration);

        assertThat(event.getCategory().toString()).isEqualTo(selectedCategory);
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
        assertThat(event.getDuration()).isEqualTo(duration);
        assertThat(event.getSpots()).isEqualTo(spots);
        assertThat(event.getLocation().toString()).isEqualTo(selectedLocation);
        assertThat(event.getDate()).isEqualTo(selectedDate);
        assertThat(event.getStartTime()).isEqualTo(startTime);
        assertThat(event.getEndTime()).isEqualTo(endTime);
    }
}