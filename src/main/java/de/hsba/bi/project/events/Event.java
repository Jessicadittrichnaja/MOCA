package de.hsba.bi.project.events;

import de.hsba.bi.project.bookingProcess.Booking;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter
    @Setter
    @Basic(optional = false)
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Category category;

    @Getter
    @Setter
    private Integer duration;

    @Getter
    @Setter
    private Integer spots;

    @Getter
    @Setter
    private Location location;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Getter
    @Setter
    private Time time;


    public Event(String name, String description, Category category, Integer duration, Integer spots, Location location, LocalDate date, Time time) {
            this.name = name;
            this.description = description;
            this.category = category;
            this.duration = duration;
            this.spots = spots;
            this.location = location;
            this.date = date;
            this.time = time;
        }

    public Event() {

    }

}