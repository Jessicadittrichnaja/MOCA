package de.hsba.bi.project.bookingProcess;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    // Mapping, um Booking-Datenbanktabelle zu erstellen mit Event- und Booking-IDs. Damit lassen sich die nötigen Datenbankabfragen leicht tätigen.

    @OneToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Event event;

    public Booking(Event event, User user) {this.event = event; this.user = user;};

    public Booking() {};
}
