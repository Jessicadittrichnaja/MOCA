package de.hsba.bi.project.roles;

// Ermöglicht Auswahl verschiedener Rollen beim Erstellen/ Ändern von Usern

import de.hsba.bi.project.bookingProcess.Booking;
import de.hsba.bi.project.events.Category;
import de.hsba.bi.project.events.Location;
import de.hsba.bi.project.events.Time;
import de.hsba.bi.project.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter
    @Setter
    @Basic(optional = false)
    private String role;


    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;

    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }


    public Role(String role) {
        this.role = role;
    }

    public Role() {

    }

    @Override
    public String toString() {
        return this.role;
    }

}
