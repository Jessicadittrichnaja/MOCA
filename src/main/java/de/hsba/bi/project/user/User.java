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

    public static String USER_ROLE = "USER";
    public static String ADMIN_ROLE = "ADMIN";


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
    private Long id;

    @Getter
    @Setter
    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String password;

    private String role;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String password, String role) {
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