package de.hsba.bi.project.filter;

import de.hsba.bi.project.events.Daytime;
import de.hsba.bi.project.events.Location;
import org.springframework.stereotype.Component;

import de.hsba.bi.project.events.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

// Ermöglicht das Filtern von Events nach verschiedenen Kategorien

@Component
public class Filter {

    @Getter
    @Setter
    private Category selectedCategory;

    @Getter
    @Setter
    private Location selectedLocation;

    @Getter
    @Setter
    private LocalDate selectedDate;

    @Getter
    @Setter
    private LocalTime selectedTime;

    @Getter
    @Setter
    private Daytime selectedDaytime;
}