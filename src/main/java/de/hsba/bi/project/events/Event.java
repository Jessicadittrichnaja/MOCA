package de.hsba.bi.project.events;

import de.hsba.bi.project.bookingProcess.Booking;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idevent", unique = true, nullable = false)
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
    private headSeminar headSeminar;

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
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @Getter
    @Setter
    private Boolean isClosed = false;

 
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    public List<Booking> getBookings() {
        if (bookings == null) {
            bookings = new ArrayList<>();
        }
        return bookings;
    }


    public Event(String name, String description, headSeminar headSeminar, Category category, Integer duration, Integer spots, Location location, LocalDate date, LocalTime startTime, LocalTime endTime) {
            this.name = name;
            this.description = description;
            this.headSeminar = headSeminar;
            this.category = category;
            this.duration = duration;
            this.spots = spots;
            this.location = location;
            this.date = date;
            this.startTime = startTime;
            this.endTime = startTime.plusHours(duration);
        }

    public Event() {
    }

}