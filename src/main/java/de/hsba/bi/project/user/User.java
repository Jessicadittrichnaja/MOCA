package de.hsba.bi.project.user;

import javax.persistence.*;

import de.hsba.bi.project.bookingProcess.Booking;
import de.hsba.bi.project.events.Event;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Comparable<User> {

    public static String MITARBEITER_ROLE = "MITARBEITER";
    public static String PERSONALABTEILUNG_ROLE = "PERSONALABTEILUNG";
    public static String TERMINVERWALTER_ROLE = "TERMINVERWALTER";

    // um angemeldeten User zu ermitteln

    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            ((UserDetails) principal).getUsername();
        }
        return ((UserDetails) principal).getUsername();
    }


    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String password;

    private Role role;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }

}