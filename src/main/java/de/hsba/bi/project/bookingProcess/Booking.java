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
/*
    @ManyToOne(optional = false)
    @Getter
    private User user;

*/

    @OneToOne
    @Getter
    private User user;

    @ManyToOne
    @Getter
    private Event event;

    public Booking(Event event, User user) {this.event = event; this.user = user;};
}
