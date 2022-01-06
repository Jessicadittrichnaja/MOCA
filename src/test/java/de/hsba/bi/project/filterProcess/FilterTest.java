package de.hsba.bi.project.filterProcess;

import de.hsba.bi.project.events.Category;
import de.hsba.bi.project.events.Daytime;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterTest {

    String              name;
    String              description;
    Category            selectedCategory;
    Integer             duration;
    Integer             spots;
    Location            selectedLocation;
    LocalDate           selectedDate;
    LocalTime           startTime;
    LocalTime           endTime;


    @Test
    @DisplayName("Filtering Events should work")
    void testFilterEvents() {
        // given
        name                = new String("Programmierung 2");
        description         = new String("Programmierveranstaltung");
        selectedCategory    = Category.Seminar;
        duration            = 3;
        spots               = 1;
        selectedLocation    = Location.Seminarraum1;
        selectedDate        = LocalDate.of(2021, 10, 23);
        startTime           = LocalTime.of(15, 30);
        endTime             = LocalTime.of(15,30).plusHours(duration);

        // when
        Event event = new Event(name, description, selectedCategory, duration, spots, selectedLocation, selectedDate, startTime,endTime);

        // then
        String name                 = "Programmierung 2";
        String description          = "Programmierveranstaltung";
        String selectedCategory     = "Seminar";
        Integer duration            =  3;
        Integer spots               =  1;
        String selectedLocation     = "Seminarraum1";
        LocalDate selectedDate      = LocalDate.of(2021,10,23);
        LocalTime startTime         = LocalTime.of(15,30);
        LocalTime endTime           = LocalTime.of(15,30).plusHours(duration);

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
