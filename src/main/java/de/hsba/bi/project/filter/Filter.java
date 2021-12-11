package de.hsba.bi.project.filter;

import de.hsba.bi.project.events.Location;
import de.hsba.bi.project.events.Time;
import org.springframework.stereotype.Component;

import de.hsba.bi.project.events.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    private Time selectedTime;

}